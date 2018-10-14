package com.udacity.gradle.builditbigger.frag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jokeandroidlib.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.free.EndpointsAsyncTask;

public class MainActivityFragment extends Fragment {
        public static TextView textView;
        public static Boolean canIntAd, resultFDelivered = false, adClosed = false;
        public static String jokeResult;
        public Boolean adLoaded, runFlag;
        public Runnable runnable;
        public Handler handler;
        public AdRequest adRequestNew;
        public ProgressBar progressBar;
        public AdView mAdView;
        View root;
        public static InterstitialAd interstitialAd;
        public Button button;
        public MainActivityFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            root = inflater.inflate(R.layout.fragment_main,container, false);
            adRequestNew = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
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
          //  progressBar = root.findViewById(R.id.progressBar);
            mAdView = root.findViewById(R.id.adView);
            canIntAd = false;
            adLoaded = false;
            runFlag = true;
            handler = new Handler();

            //int ads
            MobileAds.initialize(getActivity(),"ca-app-pub-3940256099942544~3347511713");
            interstitialAd = new InterstitialAd(getActivity());
            interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            interstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdLoaded(){
                    adLoaded = true;
                    Log.i("adLoaded = ", "true");

                }
                @Override
                public void onAdClosed(){
                    if(resultFDelivered) {
                        interstitialAd.loadAd(adRequestNew);
                        Intent intent = new Intent(getActivity(), JokeActivity.class);
                        intent.putExtra("joke", jokeResult);
                        getActivity().startActivity(intent);
                    }else{
                        adClosed = true;
                    }
                }
            });
            runnable = new Runnable() {
                @Override
                public void run() {
                    if(runFlag){
                        if(canIntAd){
                            adCheck(interstitialAd);
                            Log.i("adCheck ","is happening!");
                            Log.i("interstitialADDDD",String.valueOf(interstitialAd.isLoading()));
                        }
                        handler.post(runnable);
                    }


                }
            };

            interstitialAd.loadAd(adRequestNew);
            Log.i("last chance", String.valueOf(interstitialAd.isLoading()));
            handler.post(runnable);
            return root;
        }
        @Override
        public void onResume(){
            super.onResume();
            Log.i("onresume ", "happened");
            canIntAd = false;
            runFlag = true;
            textView.setText(getActivity().getResources().getString(R.string.instructions));
            handler.post(runnable);
            button.setVisibility(View.VISIBLE);
            interstitialAd.loadAd(adRequestNew);
          //  progressBar.setVisibility(View.INVISIBLE);

            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);



        }

        public void adCheck(InterstitialAd tempAd){
            if(adLoaded) {
                tempAd.show();
                Log.i("ad ","is showing currently");
                runFlag = false;
                adLoaded = false;
            }
        }
        public InterstitialAd intAdRef(){
            return interstitialAd;
        }

    }

