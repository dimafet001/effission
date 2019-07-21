package com.mobilityhackathon.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mobilityhackathon.app.Adapters.CustomRecyclerViewAdapter;

public class MainScreen extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        FragmentTransaction ft;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.d("test", ":::nav_main");
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.disallowAddToBackStack();
                    ft.replace(R.id.fragment, MainFragment.newInstance(null,null)).commit();
                    return true;
//                    break;

                case R.id.navigation_dashboard:
                    Log.d("test", ":::nav_voting");
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.disallowAddToBackStack();
                    ft.replace(R.id.fragment, VotingFragment.newInstance(null,null)).commit();
                    return true;
//                    break;
                case R.id.navigation_notifications:
                    Log.d("test", ":::nav_leaderboard");
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.disallowAddToBackStack();
                    ft.replace(R.id.fragment, LeaderboardFragment.newInstance(null,null)).commit();
                    return true;
//                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void checkMap(View v) {
        startActivity(new Intent(this, RoutingActivity.class));
    }
}
