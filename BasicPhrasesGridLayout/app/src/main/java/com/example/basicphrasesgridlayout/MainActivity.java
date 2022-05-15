package com.example.basicphrasesgridlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    String whichPhraseToPlay = "";

    public void phrases(View view){
        Button myPhrases = (Button) view;
        whichPhraseToPlay = myPhrases.getTag().toString();
        Log.i("InfoPhrases", whichPhraseToPlay);
        // name of a file in variable
        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(whichPhraseToPlay, "raw", getPackageName()));
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}