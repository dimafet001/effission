package com.mobilityhackathon.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilityhackathon.app.Adapters.RecyclerViewVotingAdapter;
import com.mobilityhackathon.app.data.VotingCompanyInfo;

public class VotingPageNew extends AppCompatActivity {
    private TextView mTextMessage;
    private static int choices = 0;
    //private static boolean firstChoice = false, secondChoice, ThirdChoice;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_page_new);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_voting);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String[] companyNames = {"placeholder for header", "Company1", "Company2", "Company3"};
        VotingCompanyInfo[] vcis = new VotingCompanyInfo[8];
        vcis[0] = new VotingCompanyInfo(0, "placeholder for header", false, "");
        vcis[1] = new VotingCompanyInfo(1, "Company1", false, "file:///android_asset/gmo/lastwall.png");
        vcis[2] = new VotingCompanyInfo(2, "Company2", false, "file:///android_asset/gmo/lighthouse.png");
        vcis[3] = new VotingCompanyInfo(3, "Company3", false, "file:///android_asset/gmo/shifted.png");
        vcis[4] = new VotingCompanyInfo(4, "Company4", false, "file:///android_asset/gmo/solstice.png");
        vcis[5] = new VotingCompanyInfo(5, "Company5", false, "file:///android_asset/gmo/via.png");
        vcis[6] = new VotingCompanyInfo(6, "company6", false, "file:///android_asset/gmo/edisun.png");
        vcis[7] = new VotingCompanyInfo(7, "", false, "");

        RecyclerViewVotingAdapter adapter = new RecyclerViewVotingAdapter(vcis, new RecyclerViewVotingAdapter.OnItemClicklistener() {


            @Override
            public void onItemClick(View v, VotingCompanyInfo vci) {
                Log.d("test", ":::" + VotingPageNew.choices);
                if (VotingPageNew.choices == 2) {
                    Toast.makeText(getApplicationContext(), String.format("Sorry but only two choices are allowed, unclick to deselect an item", vci.getName()), Toast.LENGTH_LONG).show();
                }
                Log.d("test2", ""+vci.isChosen());
                if (!vci.isChosen()) {
                    vci.setChosen(true);
                    if(VotingPageNew.choices < 2 && VotingPageNew.choices >= 0) {
                        v.setBackgroundColor(Color.RED);
                        VotingPageNew.choices++;
                    }
                } else {
                    vci.setChosen(false);
                    if(VotingPageNew.choices > 0 && VotingPageNew.choices <= 2) {
                        VotingPageNew.choices--;
                        v.setBackgroundColor(Color.GRAY);
                    }
                }

            }
        });
        recyclerView.setAdapter(adapter);

    }



}
