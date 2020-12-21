package com.example.hw1;

import java.util.ArrayList;

public class TopLeaders {

    private ArrayList<Leader> leaders;
    public static final int  SIZE = 10;


    public TopLeaders() {
        this.leaders=new ArrayList<Leader>();
    }

    public TopLeaders(ArrayList<Leader> leaders) {
        this.leaders = leaders;
    }

    public void addLeader(Leader leader){
        if(leaders.size() < SIZE){
            leaders.add(leader);
        }else{
            leaders.remove(0);
            leaders.add(leader);
        }
    }

    public ArrayList<Leader> getLeaders() {
        return leaders;
    }

    @Override
    public String toString() {
        return "TopLeaders{" +
                "leaders=" + leaders +
                '}';
    }

}
