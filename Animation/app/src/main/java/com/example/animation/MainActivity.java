package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean bartIsShowing = true;
    public void fadeBart (View view) {
        ImageView bartIView = (ImageView) findViewById(R.id.bartImageView);
        ImageView homerIView = (ImageView) findViewById(R.id.homerImageView);

        if (bartIsShowing) {

            //bartIView.animate().alpha(0).setDuration(2000);
            bartIView.animate().scaleX(0).scaleY(0).setDuration(1000);
            homerIView.animate().alpha(1).setDuration(3000);
            Log.i("info", "Fade Bart");
            bartIsShowing = false;

        } else {

            //bartIView.animate().alpha(1).setDuration(2000);
            bartIView.animate().scaleX(1).scaleY(1).setDuration(2000);
            homerIView.animate().alpha(0).setDuration(2000);
            Log.i("info", "Fade Homer");
            bartIsShowing = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bartIView = (ImageView) findViewById(R.id.bartImageView);
        //bartIView.animate().translationXBy(-2000).setDuration(2000);
        bartIView.setX(-2000);
        bartIView.animate().translationXBy(2000).rotation(3600).setDuration(2000);
    }
}