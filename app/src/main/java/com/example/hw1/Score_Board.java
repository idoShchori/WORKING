package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.hw1.Constants.SP_FILE;

public class Score_Board extends AppCompatActivity {

    private Fragment_list fragment_list;
    private Fragment_map fragment_map;
    private TopLeaders tl;
    private Gson gson=new Gson();
    private ImageView sbbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score__board);
        getCurrentTopLeaders();
        fragment_list=new Fragment_list();
        fragment_list.setCallBack_Top(callBack_top);
        getSupportFragmentManager().beginTransaction().add(R.id.List,fragment_list).commit();
        fragment_list.setTopTen(tl.getLeaders());
        fragment_map=new Fragment_map();
        getSupportFragmentManager().beginTransaction().add(R.id.gps_map,fragment_map).commit();

    }
    private callBack_Top callBack_top = new callBack_Top() {
        @Override
        public void displayLocation(double latitude,double longitude) {
            fragment_map.showLocation(latitude,longitude);
        }
    };
    private void getCurrentTopLeaders() {
        SharedPreferences pref=getSharedPreferences(SP_FILE,MODE_PRIVATE);
        String temp=pref.getString("topTen","Empty");
        if(temp.compareTo("Empty")==0)
        {
            tl= new TopLeaders();
        }else{
            tl= gson.fromJson(temp,TopLeaders.class);
        }
    }

}