package com.mobilityhackathon.app.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilityhackathon.app.R;
import com.mobilityhackathon.app.data.VotingCompanyInfo;
import com.squareup.picasso.Picasso;

public class RecyclerViewVotingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClicklistener {
        void onItemClick(View v, VotingCompanyInfo vci);
    }

    public interface OnButtonClick {
        void onClick();
    }

    private VotingCompanyInfo[] vcis;
    private Boolean isHeader;
    private OnItemClicklistener listener;
    private Context context;
    private final int isFooter = -1;
    //public boolean isChosen;

    public RecyclerViewVotingAdapter(VotingCompanyInfo[] names, OnItemClicklistener listener) {
        this.vcis = names;
        this.isHeader = true;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == isFooter) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_voting_page_new_footer, parent, false);
            ViewHolderFooter vh = new ViewHolderFooter(v);
            return vh;
        }
         else if (!isHeader) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_voting_page_new_row, parent, false);
            this.context = parent.getContext();
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_voting_page_new_header, parent, false);
            ViewHolderHeader vh = new ViewHolderHeader(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        //button
        if(i == vcis.length -1) {
            ViewHolderFooter vhf = (ViewHolderFooter) viewHolder;
            vhf.buttonView.setText("Cast your Vote");
            vhf.buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, String.format("you voted for: %s and %s", "bob", "bob"), Toast.LENGTH_LONG);
                }
            });
        }
        else if (!isHeader) {
            Log.d("testing3", ":::"+i);
            Log.d("testing3", ":::"+(vcis.length-1));
            Log.d("testing3", ":::"+(vcis[i].getName()));
            MyViewHolder mh = (MyViewHolder) viewHolder;
            mh.bind(vcis[i], listener);
            mh.textView.setText(vcis[i].getName());
            Picasso.with(this.context)
                    .load(vcis[i].getPathToImage())
                    .into(mh.imageView);
        } else {
            ViewHolderHeader mh = (ViewHolderHeader) viewHolder;
            mh.textView.setText("From NGO, Vote For Max 2");
            isHeader = false;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position == vcis.length-1) { return isFooter; }
        return super.getItemViewType(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return vcis.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imageView;
        public View v;

        public MyViewHolder(View v) {
            super(v);
            this.v = v;
            textView = v.findViewById(R.id.companyName);
            imageView = v.findViewById(R.id.image_company);
        }

        public void bind(final VotingCompanyInfo vci, final OnItemClicklistener listener) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //check amount of choices
                    listener.onItemClick(v, vci);
                }
            });
        }
    }

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public ViewHolderHeader(View v) {
            super(v);
            textView = v.findViewById(R.id.header_voting);
        }
    }

    public static class ViewHolderFooter extends RecyclerView.ViewHolder {
        public Button buttonView;

        public ViewHolderFooter(@NonNull final View itemView) {
            super(itemView);
            buttonView = itemView.findViewById(R.id.votingButtonSelector);
        }

    }

    public void ClickMe(View target) {
        Toast.makeText(target.getContext(), String.format("you voted for: %s and %s", vcis[1].getName(), vcis[1].getName()), Toast.LENGTH_LONG);
    }




}
//                    Toast.makeText(context, String.format("you voted for: %s and %s", vcis[0].getName(), vcis[1].getName()), Toast.LENGTH_LONG);