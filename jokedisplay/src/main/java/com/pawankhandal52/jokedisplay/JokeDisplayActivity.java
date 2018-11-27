package com.pawankhandal52.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
    
    public static final String JOKE_KEY = "joke_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
    
        Intent intent = getIntent();
        if (intent.hasExtra(JOKE_KEY)){
            TextView textView = findViewById(R.id.joke_display_textview);
            textView.setText(intent.getStringExtra(JOKE_KEY));
        }
    }
}
