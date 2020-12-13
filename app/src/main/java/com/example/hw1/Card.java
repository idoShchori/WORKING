
package com.example.hw1;

        import android.media.Image;
        import android.util.Log;

public class Card {

    private int value;
    private String img;
    public Card(){
        this.value=0;
        this.img="";
    }

    public int getValue() {
        return this.value;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setValue(int value) {
        this.value = value;
    }
}



