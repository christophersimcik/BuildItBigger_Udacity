package com.example.jokeandroidlib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link jokeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link jokeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class jokeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public Handler handler;
    public int alphaValue;
    public Runnable r;
    public Boolean fader = true;
    TextView textView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public jokeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment jokeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static jokeFragment newInstance(String param1, String param2) {
        jokeFragment fragment = new jokeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        if (getArguments() != null) {
            Bundle recieveBundle = new Bundle();
            recieveBundle = getArguments();
            mParam1 = (String) recieveBundle.get("joke");
            //mParam1 = getArguments().getString(ARG_PARAM1);
          //  mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
      //  Log.i("value of bundle ", mParam1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String gceResult = getActivity().getIntent().getStringExtra("gce_result");
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        textView = view.findViewById(R.id.joke_display_textview);
        gceResult = mParam1;
        textView.setText(gceResult);
        alphaValue = 255;
        r = new Runnable() {
            public void run() {
                    alphaValue -= 2;
            if(alphaValue < 2) {
                alphaValue = 2;
                fader = false;
                getActivity().finish();
            }
                    textView.setTextColor(textView.getTextColors().withAlpha(alphaValue));
                    if(fader) {
                        handler.postDelayed(r,10);
                    }
                }


        };
        handler.post(r);
        return view;

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
