package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class WinnerWinnerChickenDinner extends AppCompatActivity {
    private TextView winner;
    private Button ext;
    private Button rstrt;
    private Button menu;
    private ImageView wbg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_winner_chicken_dinner);
        winner=findViewById(R.id.winner);
        ext=findViewById(R.id.Exit);
        rstrt=findViewById(R.id.Play_Again);
        wbg=findViewById(R.id.winner_PNG_background);
        Glide.with(this).load("https://image.shutterstock.com/image-vector/winner-background-signs-first-second-600w-1289845030.jpg").into(wbg);
        menu=findViewById(R.id.BackToOpening);
        playSound(R.raw.winner);


        winner.setText(getIntent().getStringExtra("Winner"));
        rstrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.sound1);
                Intent myIntent = new Intent(WinnerWinnerChickenDinner.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.sound1);
                finish();

            }
        });
        ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.sound1);
                finish();
            }
        });

    }
    MediaPlayer mp;
    private void playSound(int rawId) {
        if (mp!=null  &&  mp.isPlaying()) {
            mp.reset();
            mp.release();
            mp = null;
        }
        mp = MediaPlayer.create(this, rawId);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp=null;
            }
        });
        mp.start();
    }
}