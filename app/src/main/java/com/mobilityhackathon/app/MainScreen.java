package com.mobilityhackathon.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.mobilityhackathon.app.Adapters.CustomRecyclerViewAdapter;

public class MainScreen extends AppCompatActivity {
    private TextView mTextMessage;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.d("test", ":::nav_home");
                    Intent i = new Intent(getBaseContext(), MainScreen.class);
                    startActivity(i);
                    return true;
                    //return true;
                case R.id.navigation_dashboard:
                    Log.d("test", ":::nav_dashboard");
                    Intent i2 = new Intent(getApplicationContext(), VotingPage.class);
                    startActivity(i2);
                    //return true;
                    break;
                case R.id.navigation_notifications:
                    Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        recyclerView = findViewById(R.id.my_recycler_view);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        String[] info = {"Your carbon footprint is approximately 870.000", "Your company reduced their weekly intake by 20%", "You are 30% better than the average company"};

        mAdapter = new CustomRecyclerViewAdapter(info);
        recyclerView.setAdapter(mAdapter);
    }

}
