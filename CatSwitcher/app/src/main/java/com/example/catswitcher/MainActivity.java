package com.example.catswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean catShowing = true;
    public void switchCat(View view){
        if (catShowing) {
            catShowing = false;
            Log.i("info", "Cat 2 is showing");
            ImageView image = (ImageView) findViewById(R.id.catImageView);
            image.setImageResource(R.drawable.cat2);
        }
        else {
            catShowing = true;
            Log.i("info", "Cat 1 is showing");
            ImageView image = (ImageView) findViewById(R.id.catImageView);
            image.setImageResource(R.drawable.cat);
        }
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}