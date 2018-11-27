/*
 * Copyright (C) 2018 The Android Nanodegree Project made under Udacity Nanodegree Course
 * Author Pawan Kumar Sharma
 * All Rights Reserved
 */
package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pawankhandal52.englishjoke.EnglishJoke;
import com.pawankhandal52.jokedisplay.JokeDisplayActivity;

/**
 * This the Launcher Screen which attache a fragment to itself.
 */
public class MainActivity extends AppCompatActivity {
    private EnglishJoke englishJoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        englishJoke = new EnglishJoke();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        String joke  = englishJoke.getAJoke();
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_KEY,joke);
        startActivity(intent);
    }


}
