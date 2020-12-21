package com.example.hw1;

public class Leader {

    private int score;
    private String name;
    private double lat;
    private double lon;

    public Leader(){
        this.name="";
        this.lat=0;
        this.lon=0;
        this.score=0;

    }
    public Leader(String name, double lat ,double lon,int score){
        this.name=name;
        this.lat=lat;
        this.lon=lon;
        this.score=score;

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getLat() {
        return lat;
    }

    public String getName() {
        return name;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }
}

