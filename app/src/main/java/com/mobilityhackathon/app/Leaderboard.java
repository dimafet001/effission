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

import com.mobilityhackathon.app.Adapters.LeaderBoardAdapter;

public class Leaderboard extends AppCompatActivity {
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
//                case R.id.navigation_leaderboard:
//                    Intent i4 = new Intent(getApplicationContext(), Leaderboard.class);
//                    startActivity(i4);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_leaderboard);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String[] companyNames = {"bob", "bob2", "bob3", "EcologicalSexualSuperFetishMultiDildo"};
        Integer[] points = {10, 20, 30, 40};
        Integer[] positions = {1,2,3,4};
        LeaderBoardAdapter adapter = new LeaderBoardAdapter(companyNames, points, positions);
        recyclerView.setAdapter(adapter);
    }

}
