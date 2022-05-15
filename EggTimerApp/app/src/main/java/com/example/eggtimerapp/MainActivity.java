package com.example.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView minuteTextView, secondTextView;
    boolean isButtonPressed = false;
    SeekBar seekBar;
    Button button;
    CountDownTimer myCounter;

    public void updateTimerTextViews(int value){
        int y = value/60;
        int x = value%60;
        NumberFormat formatter = new DecimalFormat("00");
        String minute = String.valueOf(y);
        String second = formatter.format(x);
        // set values into minute and second TextViews
        minuteTextView.setText(minute+":");
        secondTextView.setText(second);
    }

    public void pauseTimer(){
        button.setText("GO!");
        seekBar.setEnabled(true);
        myCounter.cancel();
        isButtonPressed = false;
    }

    public void startTimer(){
        isButtonPressed = true;
        seekBar.setEnabled(false);
        button.setText("PAUSE!");

        // countdown
        myCounter = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                l = l / 1000;
                updateTimerTextViews((int) l);
                Log.i("countDown", String.valueOf(l));
                seekBar.setProgress(Integer.parseInt(String.valueOf(l)));

                if (l < 10) {
                    minuteTextView.setTextColor(getResources().getColor(R.color.red));
                    secondTextView.setTextColor(getResources().getColor(R.color.red));
                }

            }

            @Override
            public void onFinish() {
                secondTextView.setText("00");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                mediaPlayer.start();
                button.setText("GO!");
                seekBar.setEnabled(true);
                // reset textColor to black
                minuteTextView.setTextColor(getResources().getColor(R.color.ligth_black));
                secondTextView.setTextColor(getResources().getColor(R.color.ligth_black));

            }
        }.start();
    }
    public void seekBarValue(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if( i > 0) {
                    updateTimerTextViews(i);
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


// Call all of the methods => start and pause timer
    public void countDownMe(View view){
        if(isButtonPressed){
           pauseTimer();
        } else {
            startTimer();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minuteTextView = (TextView) findViewById(R.id.textView1);
        secondTextView = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setMax(120);
        seekBarValue();
    }
}