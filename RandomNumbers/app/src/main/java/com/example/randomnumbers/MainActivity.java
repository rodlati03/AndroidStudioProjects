package com.example.randomnumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int min = 1;
    int max = 20;
    int myGuessingNumber;
    String message;
    int resultRandom;
    public void onClick(View view){
        EditText guessingEditText = (EditText) findViewById(R.id.guessEditText);
        myGuessingNumber = Integer.parseInt(guessingEditText.getText().toString());

        if ( myGuessingNumber > resultRandom){

            message = "Go Lower !";

        }else if( myGuessingNumber < resultRandom ){

            message = "Go Higher !";

        } else {

            message = "BRAVOooo, you got it! Play again (^_^)";
            // reiterate the random value
            Random rand = new Random();
            resultRandom = rand.nextInt(max) +1;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // first way to do that
        //resultRandom = (int)(Math.random()*(max-min+1)+min);
        // second way to do that
        Random rand = new Random();
        resultRandom = rand.nextInt(max) +1;
    }
}