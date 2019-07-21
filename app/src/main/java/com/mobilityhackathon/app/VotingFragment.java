package com.mobilityhackathon.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilityhackathon.app.Adapters.CustomAdapter;
import com.mobilityhackathon.app.data.SubjectData;

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
        View root = inflater.inflate(R.layout.fragment_voting, container, false);

        mTextMessage = root.findViewById(R.id.message);

        ListView list = root.findViewById(R.id.list);
        final ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();


        arrayList.add(new SubjectData("JAVA", "https://www.tutorialspoint.com/java/", "file:///android_asset/gmo/edisun.png"));
        arrayList.add(new SubjectData("Python", "https://www.tutorialspoint.com/python/", "file:///android_asset/gmo/lastwall.png"));
        arrayList.add(new SubjectData("Javascript", "https://www.tutorialspoint.com/javascript/", "file:///android_asset/gmo/lighthouse.png"));
        arrayList.add(new SubjectData("Cprogramming", "https://www.tutorialspoint.com/cprogramming/", "file:///android_asset/gmo/shifted.png"));
        arrayList.add(new SubjectData("Cplusplus", "https://www.tutorialspoint.com/cplusplus/", "file:///android_asset/gmo/solstice.png"));
        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/", "file:///android_asset/gmo/via.png"));

        CustomAdapter customAdapter = new CustomAdapter(getContext(), arrayList);
        list.setAdapter(customAdapter);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Log.d("aha", "here");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("testing", ":::inside OnItemClick");
                Toast.makeText(getContext(), arrayList.get(i) + "", Toast.LENGTH_SHORT).show();
            }

        });

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
