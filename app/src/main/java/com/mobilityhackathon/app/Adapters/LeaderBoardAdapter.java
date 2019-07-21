package com.mobilityhackathon.app.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobilityhackathon.app.R;

import java.util.HashMap;
import java.util.Map;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.MyViewHolder> {
    private String[] names;
    private Integer[] points, positions;


    // Provide a suitable constructor (depends on the kind of dataset)
    public LeaderBoardAdapter(String[] names, Integer[] points, Integer[] positions) {
        this.names = names;
        this.points = points;
        this.positions = positions;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LeaderBoardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_leaderboard_row, parent, false);

        LeaderBoardAdapter.MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LeaderBoardAdapter.MyViewHolder holder, int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        holder.textView.setText(names[position]);
        holder.points.setText(points[position].toString());
        holder.position.setText(positions[position].toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return names.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView points;
        public TextView position;

        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.topUsersLeaderboardItem_username);
            points = v.findViewById(R.id.topUsersLeaderboardItem_score);
            position = v.findViewById(R.id.topUsersLeaderboardItem_position);
        }
    }
}
