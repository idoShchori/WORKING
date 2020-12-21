package com.example.hw1;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

public class Logics {



    private Card cards[][]=new Card[2][26];
    private int p1Score;
    private int p2Score;

    public Logics(){

        this.p1Score=0;
        this.p2Score=0;
        for (int i = 0; i <2; i++) {
            for (int j = 0; j <26 ; j++) {
                cards[i][j]=new Card();
            }
        }
        for (int i = 1; i < 53; i++) {//splitting the deck

            if((i/26)<1){
                if(i%13==0){//in case of ace
                    this.cards[0][i-1].setValue(13);
                }else{
                    this.cards[0][i-1].setValue(i%13);
                }
                this.cards[0][i-1].setImg("poker_"+(i+1)+"_");

            }else {
                if(i==26)
                    this.cards[1][(i-1) % 26].setImg("poker_"+(27)+"_");//for some reason only"poker_27" can be read

                if(i%13==0){
                    this.cards[1][(i-1) % 26].setValue(13);
                }else{
                    this.cards[1][(i-1) % 26].setValue(i%13);
                }
                this.cards[1][(i-1) % 26].setImg("poker_"+(i+1)+"_");
            }




        }
        for (int i = 0; i <2; i++) {
            for (int j = 0; j <26 ; j++) {


            }
        }

    }
    public String[] oneGame() {//randomize the current card for each player
        String[] images = new String[2];
        int temp1= (int)(Math.random() * 26);
        int temp2= (int)(Math.random() * 26);

        Card p1c = this.cards[0][temp1];
        Card p2c = this.cards[1][temp2];
        images[0] = p1c.getImg();//setting the right image for eah player
        images[1] = p2c.getImg();
        if (this.cards[0][temp1].getValue() > this.cards[1][temp2].getValue()) {
            this.p1Score++;

        } else if (this.cards[0][temp1].getValue() < this.cards[1][temp2].getValue()) {
            this.p2Score++;
        }
        return images;
    }
    public int getP1Score(){
        return this.p1Score;
    }
    public int getP2Score(){
        return this.p2Score;
    }










}
