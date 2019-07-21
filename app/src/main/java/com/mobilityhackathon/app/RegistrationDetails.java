package com.mobilityhackathon.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mobilityhackathon.app.DisplayInfoActivity;

import com.smartcar.sdk.SmartcarAuth;
import com.smartcar.sdk.SmartcarCallback;
import com.smartcar.sdk.SmartcarResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistrationDetails extends AppCompatActivity {

    private static String CLIENT_ID;
    private static String REDIRECT_URI;
    private static String[] SCOPE;
    private SmartcarAuth smartcarAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_details);

        String make, model, year;
        Intent intent = getIntent();
        if (intent.hasExtra("make")) {
            make = intent.getStringExtra("make");
            model = intent.getStringExtra("model");
            year = intent.getStringExtra("year");

            findViewById(R.id.input_car_make).set
        } else {

            CLIENT_ID = getString(R.string.client_id);
            REDIRECT_URI = "sc" + getString(R.string.client_id) + "://exchange";
            SCOPE = new String[]{"required:read_vehicle_info"};

            smartcarAuth = new SmartcarAuth(
                    CLIENT_ID,
                    REDIRECT_URI,
                    SCOPE,
                    false,
                    new SmartcarCallback() {
                        @Override
                        public void handleResponse(final SmartcarResponse smartcarResponse) {
                            Log.i("mylog", smartcarResponse.getCode());

                            // TODO: Request Step 1: Obtain an access token
                            final OkHttpClient client = new OkHttpClient();

                            // TODO: Request Step 1: Obtain and access token

                            // Request can not run on the Main Thread
                            // Main Thread is used for UI and therefore can not be blocked
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    // send request to exchange the auth code for the access token
                                    Request exchangeRequest = new Request.Builder()
                                            .url(getString(R.string.app_server) + "/exchange?code=" + smartcarResponse.getCode())
                                            .build();

                                    try {
                                        client.newCall(exchangeRequest).execute();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                    // send request to retrieve the vehicle info
                                    Request infoRequest = new Request.Builder()
                                            .url(getString(R.string.app_server) + "/vehicle")
                                            .build();

                                    try {
                                        Response response = client.newCall(infoRequest).execute();

                                        String jsonBody = response.body().string();
                                        Log.d("mylog", jsonBody);
                                        JSONObject JObject = new JSONObject(jsonBody);

                                        String make = JObject.getString("make");
                                        String model = JObject.getString("model");
                                        String year = JObject.getString("year");

                                        Log.d("mylog", make + model + year);
                                        Intent intent = new Intent(getApplicationContext(), RegistrationDetails.class);
//                                    intent.putExtra("INFO", make + " " + model + " " + year);
                                        intent.putExtra("make", make);
                                        intent.putExtra("model", model);
                                        intent.putExtra("year", year);
                                        startActivity(intent);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                            // TODO: Request Step 2: Get vehicle information

                        }
                    }
            );

            Button connectButton = findViewById(R.id.btn_connect);
            smartcarAuth.addClickHandler(getApplicationContext(), connectButton);

        }
    }


    public void ConnectClick(View view) {
        finish();
    }
}
