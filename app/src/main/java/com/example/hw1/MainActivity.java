package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.hw1.Constants.SP_FILE;

public class MainActivity extends AppCompatActivity {
    final int DELAY =1000;
    public static final int WIN=8;
    private String[] imgs=new String[2];
    private ImageButton button1;
    private ImageView p1i;
    private ImageView p2i;
    private ImageView back;
    private ImageView p1;
    private ImageView p2;
    private TextView score1;
    private TextView score2;
    private String name1="";
    private String name2="";
    private Logics game;
    private ProgressBar pb;
    private boolean isClicked=false;
    private TopLeaders leaders;
    private Gson gson=new Gson();



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
        Glide.with(this)
                .load("https://thumbs.dreamstime.com/z/man-icon-person-vector-worker-162495520.jpg")
                .into(p1);
        Glide.with(this)
                .load("https://image.freepik.com/free-vector/poker-table-background-green-color_47243-1068.jpg")
                .into(back);
        Glide.with(this)
                .load("https://thumbs.dreamstime.com/z/man-icon-person-vector-worker-162495520.jpg")
                .into(p2);


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
            createWinner("Player 1",OpeningScreen.latitude,OpeningScreen.longitude,game.getP1Score());
            return "Player 1 Win's";
        }
        else if(game.getP1Score()<game.getP2Score()) {
            createWinner("Player 2",OpeningScreen.latitude,OpeningScreen.longitude,game.getP2Score());
            return "Player 2 Win's";
        }else{
            return "";
        }

    }


    private void createWinner(String s, double i, double i1, int pScore) {
        leaders=getCurrentTopLeaders();
        leaders.addLeader(new Leader(s,i,i1,pScore));
        SharedPreferences pref=getSharedPreferences(SP_FILE,MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("topTen",gson.toJson(leaders));
        editor.apply();
    }

    private TopLeaders getCurrentTopLeaders() {
        SharedPreferences pref=getSharedPreferences(SP_FILE,MODE_PRIVATE);
        String temp=pref.getString("topTen","Empty");
        Log.d("pttt",temp);
        if(temp.compareTo("Empty")==0)
        {
            return new TopLeaders();
        }else{
            return gson.fromJson(temp,TopLeaders.class);
        }
    }

    private void findViews()
    {
        p1=findViewById(R.id.Player1);
        p2=findViewById(R.id.Player2);
        back=findViewById(R.id.main_PNG_background);
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