package com.example.timersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void timer (View view){

        Button timerButton = (Button) findViewById(R.id.button);
        EditText editTextMaxTimer = (EditText) findViewById(R.id.editTextNumber);
        TextView postCountTimer = (TextView) findViewById(R.id.postTextView);
        TextView secondLeftSign = (TextView) findViewById(R.id.secondLeftTextView);
        secondLeftSign.setVisibility(View.VISIBLE);
        postCountTimer.setTextColor(getResources().getColor(R.color.black));


        int enterMaxTimer = Integer.parseInt(editTextMaxTimer.getText().toString());
        enterMaxTimer = enterMaxTimer * 1000;
        postCountTimer.setText(String.valueOf(enterMaxTimer/1000));
        new CountDownTimer(enterMaxTimer, 1000) {
            @Override
            public void onTick(long milisecondsUntilDone) {
                postCountTimer.setTextSize(90);
                Log.i("Seconds left" , String.valueOf( milisecondsUntilDone / 1000));
                postCountTimer.setText(String.valueOf(milisecondsUntilDone / 1000));
            }

            @Override
            public void onFinish() {
                secondLeftSign.setVisibility(View.INVISIBLE);
                postCountTimer.setTextSize(30);
                postCountTimer.setText("TIME IS UP !");
                postCountTimer.setTextColor(getResources().getColor(R.color.red));
                Log.i("We're done!", "No more CountDown");
            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView postCountTimer = (TextView) findViewById(R.id.postTextView);
        postCountTimer.setTextColor(getResources().getColor(R.color.black));
    }
}