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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.pawankhandal52.jokedisplay.JokeDisplayActivity;

/**
 * A fragment which shows a button and where user can retive the joke from GCE
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.JokeRecciveInterface {
    
    private Context mContext;
    private ProgressBar mProgressBar;
    public MainActivityFragment() {
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getActivity();
        Button button = root.findViewById(R.id.tell_joke_button);
        mProgressBar = root.findViewById(R.id.progressbar);
        
    
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                tellJoke();
            }
        });
        return root;
    }
    
    public void tellJoke() {
        /*String joke  = englishJoke.getAJoke();
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        */
        new EndpointsAsyncTask(this).execute();
    }
    
    @Override
    public void onJokeReceive(String data) {
        mProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getActivity(),JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_KEY,data);
        startActivity(intent);
    }
}
