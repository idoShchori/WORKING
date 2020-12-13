package com.example.hw1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class   OpeningScreen extends AppCompatActivity {
    private Button ply;
    private Button sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openingscren);
        ply = findViewById(R.id.Play);
        sb = findViewById(R.id.ScoreBoard);
        //final MediaPlayer mp = MediaPlayer.create(this, R.);
        ply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(OpeningScreen.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(OpeningScreen.this, Score_Board.class);
                startActivity(myIntent);
                finish();
            }
        });
    }


}