package com.example.hw1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;


public class OpeningScreen extends AppCompatActivity {
    private Button ply;
    private Button sb;
    private ImageView bg;
    private LocationManager lm;
    private Location location;
    public static double longitude;
    public static double latitude;
    private static final int  REQUEST_CODE=1;


    private final LocationListener ll = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            try{
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }catch(Exception e){
                longitude=0;
                latitude=0;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openingscren);
        ply = findViewById(R.id.Play);
        sb = findViewById(R.id.ScoreBoard);
        bg = findViewById(R.id.Opening_PNG_background);
        Glide.with(this).load("https://image.freepik.com/free-vector/poker-table-background-green-color_47243-1068.jpg").into(bg);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (ActivityCompat.shouldShowRequestPermissionRationale(OpeningScreen.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to access GoogleMap feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(OpeningScreen.this,
                                        new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                                        REQUEST_CODE);
                            }
                        })
                        .create()
                        .show();

            }else{
                ActivityCompat.requestPermissions(OpeningScreen.this,
                        new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                        REQUEST_CODE);
            }
                return;
        }
        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        ll.onLocationChanged(location);
        startGame();
        showScoreBoard();
        Log.d("pttt","lon:"+longitude+"  lat:"+ latitude );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(OpeningScreen.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        ll.onLocationChanged(location);
                    Log.d("pttt","lon:"+longitude+"  lat:"+ latitude );
                    startGame();
                    showScoreBoard();
                }
            }else{

                longitude=0;
                latitude=0;
                startGame();
                showScoreBoard();
                Log.d("pttt","lon:"+longitude+"  lat:"+ latitude );

            }


        }

    }
    public void startGame(){
        ply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(OpeningScreen.this, MainActivity.class);
                startActivity(myIntent);
                //finish();
            }
        });

    }
    public void showScoreBoard(){
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(OpeningScreen.this, Score_Board.class);
                startActivity(myIntent);
                // finish();
            }
        });

    }



}