package com.example.storepermanentdatawithsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.storepermanentdatawithsharedpreferences", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("username", "Jonathan").apply();
        //String username = sharedPreferences.getString("username","");
        // Log.i("Username data",username);

        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Ramzi");
        friends.add("Django");
        friends.add("Jeremy");


        try {
            //ObjectSerializer.serialize(friends).toString();
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
            Log.i("friends",ObjectSerializer.serialize(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }



        ArrayList<String> newFriends = new ArrayList<String>();

        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("New Friends", newFriends.toString());

    }
}