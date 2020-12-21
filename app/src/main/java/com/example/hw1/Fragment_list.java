package com.example.hw1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_list extends Fragment {


    private ListView leaders_List;
    private ArrayList<Leader> topTen;
    private callBack_Top cbt;
    public void setCallBack_Top(callBack_Top cbt){
        this.cbt=cbt;
    }
    private void findViews(View view){
        leaders_List=view.findViewById(R.id.leadersList);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        findViews(view);
        initViews(view);
        return view;
    }

    public void initViews(View view){
        List<Map<String,String>> data=new ArrayList<>();
        int topTenSize=topTen.size()-1;
        for (int i = topTenSize; i >=0 ; i--) {
            Map<String,String> record=new HashMap<>();
            record.put("name",topTen.get(i).getName());
            record.put("score",topTen.get(i).getScore()+"");
            data.add(record);
        }
        SimpleAdapter adapter= new SimpleAdapter(
                view.getContext(),
                data,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "score"},
                new int[]{android.R.id.text1,android.R.id.text2}
                );
        leaders_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {//when a leader gets clicked
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (cbt!=null){
                    cbt.displayLocation(topTen.get(topTenSize-position).getLat(),
                            topTen.get(topTenSize-position).getLon());
                }
            }
        });
        leaders_List.setAdapter(adapter);
    }
    public void setTopTen(ArrayList<Leader>topTen){
        this.topTen=topTen;
    }

}
