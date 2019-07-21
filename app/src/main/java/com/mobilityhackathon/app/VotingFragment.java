package com.mobilityhackathon.app;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilityhackathon.app.Adapters.CustomAdapter;
import com.mobilityhackathon.app.Adapters.RecyclerViewVotingAdapter;
import com.mobilityhackathon.app.data.SubjectData;
import com.mobilityhackathon.app.data.VotingCompanyInfo;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VotingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VotingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VotingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static int choices = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mTextMessage;

    private OnFragmentInteractionListener mListener;

    public VotingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VotingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VotingFragment newInstance(String param1, String param2) {
        VotingFragment fragment = new VotingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_voting_page_new, container, false);

        mTextMessage = root.findViewById(R.id.message);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_voting);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        String[] companyNames = {"placeholder for header", "Company1", "Company2", "Company3"};
        VotingCompanyInfo[] vcis = new VotingCompanyInfo[8];
        vcis[0] = new VotingCompanyInfo(0, "placeholder for header", false, "");
        vcis[1] = new VotingCompanyInfo(1, "Lastwall", false, "file:///android_asset/gmo/lastwall.png");
        vcis[2] = new VotingCompanyInfo(2, "Carbon Lighthouse", false, "file:///android_asset/gmo/lighthouse.png");
        vcis[3] = new VotingCompanyInfo(3, "Shifted Energy", false, "file:///android_asset/gmo/shifted.png");
        vcis[4] = new VotingCompanyInfo(4, "Solstice", false, "file:///android_asset/gmo/solstice.png");
        vcis[5] = new VotingCompanyInfo(5, "VIA", false, "file:///android_asset/gmo/via.png");
        vcis[6] = new VotingCompanyInfo(6, "Edisun Microbrids", false, "file:///android_asset/gmo/edisun.png");
        vcis[7] = new VotingCompanyInfo(7, "", false, "");

        RecyclerViewVotingAdapter adapter = new RecyclerViewVotingAdapter(vcis, new RecyclerViewVotingAdapter.OnItemClicklistener() {


            @Override
            public void onItemClick(View v, VotingCompanyInfo vci) {
                Log.d("test", ":::" + choices);
                if (choices == 2) {
                    Toast.makeText(getContext(), String.format("Sorry but only two choices are allowed, click to unselect an item", vci.getName()), Toast.LENGTH_LONG).show();
                }
                Log.d("test2", ""+vci.isChosen());
                if (!vci.isChosen()) {
                    vci.setChosen(true);
                    if(choices < 2 && choices >= 0) {
                        v.setBackgroundColor(getResources().getColor(R.color.highlightChoice));
                        choices++;
                    }
                } else {
                    vci.setChosen(false);
                    if(choices > 0 && choices <= 2) {
                        choices--;
                        v.setBackgroundColor(Color.GRAY);
                    }
                }

            }
        });
        recyclerView.setAdapter(adapter);

        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
