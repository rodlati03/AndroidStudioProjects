package com.example.timetableswithseekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.myListView);
        SeekBar mySeekBar = (SeekBar) findViewById(R.id.seekBar);
        mySeekBar.setProgress(1);
       // mySeekBar.setMax(20);

        ArrayList<Integer> initialList = new ArrayList<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        for(int y=0; y<list.size(); y++) {
            initialList.add(list.get(y));
        }

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                list.clear();
                for(int y=0; y<initialList.size(); y++) {
                    list.add(initialList.get(y));
                    listView.setAdapter(arrayAdapter);
                }
                if ( i != 0) {
                    for(int y=0; y<list.size(); y++) {
                        list.set(y, list.get(y) * i);
                        listView.setAdapter(arrayAdapter);
                        Log.i("Log", Integer.toString(i));
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}