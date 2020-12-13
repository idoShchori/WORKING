package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    final int DELAY =1000;
    public static final int WIN=8;
    private String[] imgs=new String[2];
    private ImageButton button1;
    private ImageView p1i;
    private ImageView p2i;
    private TextView score1;
    private TextView score2;
    private String name1="";
    private String name2="";
    private Logics game;
    private ProgressBar pb;
    private boolean isClicked=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        game=new Logics();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicked=true;
                button1.setEnabled(false);
                startCounting();

            }
        });

    }
    private void changeScore(Logics game) {
        score1.setText(""+game.getP1Score());
        score2.setText(""+game.getP2Score());
        if(game.getP1Score()>game.getP2Score()){
            pb.setProgress(game.getP1Score());
        }else{
            pb.setProgress(game.getP2Score());
        }

    }
    public void changeImg(String name1,String name2){
        int img1=this.getResources().getIdentifier(name1, "drawable", this.getPackageName());
        int img2=this.getResources().getIdentifier(name2, "drawable", this.getPackageName());
        p1i.setImageResource(img1);
        p2i.setImageResource(img2);

    }
    public String whoWin(Logics game) {
        if (game.getP1Score()>game.getP2Score()){
            return "Ido Win's";
        }
        else if(game.getP1Score()<game.getP2Score()) {
            return "Stav Win's";
        }else{
            return "";
        }

    }
    private void findViews()
    {
        button1 =findViewById(R.id.shuffle);
        p1i =findViewById(R.id.Left_Card);
        p2i=findViewById(R.id.Right_Card);
        score1=findViewById(R.id.left_Player_Score);
        score2=findViewById(R.id.right_Player_Score);
        pb=findViewById(R.id.prog);


    }

    final Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            handler.postDelayed(this, DELAY);
            secondlyFunction();
        }
    };


    private void startCounting() {
        handler.postDelayed(runnable, DELAY);
    }

    private void secondlyFunction()
    {
        if (game.getP1Score() != WIN && game.getP2Score() != WIN) {
            imgs = game.oneGame();
            playSound(R.raw.cards);
            changeImg(imgs[0], imgs[1]);
            changeScore(game);
        } else {//check winner and send massage to next window
            String Win = whoWin(game);
            Intent myIntent = new Intent(MainActivity.this, WinnerWinnerChickenDinner.class);
            myIntent.putExtra("Winner", Win);
            startActivity(myIntent);
            finish();
        }

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
                mp.release();
                mp=null;
            }
        });
        mp.start();
    }


    private void stopCounting() {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isClicked)
            startCounting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCounting();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}