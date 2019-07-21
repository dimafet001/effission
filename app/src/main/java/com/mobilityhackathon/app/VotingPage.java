package com.mobilityhackathon.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilityhackathon.app.Adapters.CustomAdapter;
import com.mobilityhackathon.app.data.SubjectData;

import java.util.ArrayList;

public class VotingPage extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.d("test", ":::nav_home");
                    Intent i = new Intent(getBaseContext(), MainScreen.class);
                    startActivity(i);
                    break;
                //return true;
                case R.id.navigation_dashboard:
                    Log.d("test", ":::nav_dashboard");
                    Intent i2 = new Intent(getApplicationContext(), VotingPage.class);
                    startActivity(i2);
                    //return true;
                    break;
                case R.id.navigation_notifications:
//                    Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(i3);
                    break;
                //return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_page);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ListView list = findViewById(R.id.list);
        final ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();


        arrayList.add(new SubjectData("JAVA", "https://www.tutorialspoint.com/java/","file:///android_asset/gmo/edisun.png"));
        arrayList.add(new SubjectData("Python", "https://www.tutorialspoint.com/python/", "file:///android_asset/gmo/lastwall.png"));
        arrayList.add(new SubjectData("Javascript", "https://www.tutorialspoint.com/javascript/", "file:///android_asset/gmo/lighthouse.png"));
        arrayList.add(new SubjectData("Cprogramming", "https://www.tutorialspoint.com/cprogramming/", "file:///android_asset/gmo/shifted.png"));
        arrayList.add(new SubjectData("Cplusplus", "https://www.tutorialspoint.com/cplusplus/", "file:///android_asset/gmo/solstice.png"));
        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/", "file:///android_asset/gmo/via.png"));

        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Log.d("aha", "here");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("testing", ":::inside OnItemClick");
                Toast.makeText(getApplicationContext(), arrayList.get(i)+"", Toast.LENGTH_SHORT).show();
            }

        });
    }


}
