package com.udacity.gradle.builditbigger.frag;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.paid.EndpointsAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
public static TextView textView;
public static String jokeResult;
public ProgressBar progressBar;
View root;
public Button button;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main,container, false);
        textView = root.findViewById(R.id.instructions_text_view);
        button = root.findViewById(R.id.instructions_text_view_button);
        button.setVisibility(View.VISIBLE);
        button.setTextColor(Color.parseColor("#FAFAFA"));
        button.setTextSize(18);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("button test ","button clicked");
                button.setVisibility(View.INVISIBLE);
                textView.setText("getting a joke...");
                new EndpointsAsyncTask(getContext(),progressBar).execute(new Pair<Context, String>(getContext(), "retrieveJoke"));

            }
        });
        progressBar = root.findViewById(R.id.progressBar);

        return root;
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.i("onresume ", "happened");
        button.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
