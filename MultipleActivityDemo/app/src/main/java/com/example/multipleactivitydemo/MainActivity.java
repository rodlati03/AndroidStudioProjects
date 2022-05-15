package com.example.multipleactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayList<String> myFamily = new ArrayList<String>();
        myFamily.add("Jonathan");
        myFamily.add("Sophie");
        myFamily.add("Rachel");
        myFamily.add("Corine");
        myFamily.add("Lourdine");
        myFamily.add("Marline");
        myFamily.add("Senèque");
        myFamily.add("Antoinette");
        myFamily.add("Michael");
        myFamily.add("Arnaud");
        myFamily.add("Antonio");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("my family", myFamily.get(i));
                goToNext(myFamily.get(i).toString());
            }
        });
    }
    public void goToNext(String name){
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        // sending data to the other activity
        intent.putExtra("Name", name);
        startActivity(intent);



    }
}