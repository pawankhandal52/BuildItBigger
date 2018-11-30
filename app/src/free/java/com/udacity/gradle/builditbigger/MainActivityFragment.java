/*
 * Copyright (C) 2018 The Android Nanodegree Project made under Udacity Nanodegree Course
 * Author Pawan Kumar Sharma
 * All Rights Reserved
 */
package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.pawankhandal52.jokedisplay.JokeDisplayActivity;

/**
 * A fragment which shows a button and where user can retive the joke from GCE
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.JokeRecciveInterface {
    
    private Context mContext;
    private InterstitialAd mInterstitialAd;
    private ProgressBar mProgressBar;
    public MainActivityFragment() {
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
    
        mContext = getActivity();
        AdView mAdView =  root.findViewById(R.id.adView);
        Button button = root.findViewById(R.id.tell_joke_button);
        mProgressBar = root.findViewById(R.id.progressbar);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        
        //For interstitial add
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(mContext.getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                mProgressBar.setVisibility(View.VISIBLE);
                tellJoke();
                
            }
        });
        return root;
    }
    
    private  void tellJoke() {
        /*String joke  = englishJoke.getAJoke();
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        */
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }
    
    @Override
    public void onJokeReceive(String data) {
        mProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getActivity(),JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_KEY,data);
        mContext.startActivity(intent);
        
    }
}
