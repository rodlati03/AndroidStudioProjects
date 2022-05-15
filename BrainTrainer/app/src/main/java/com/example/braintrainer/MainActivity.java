package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Variables declarations
    Button timerButton, redButton, purpleButton, blueButton, greenButton, winOrLooseButton, buttonPlayAgain;
    TextView postSum, winOrLoose, winOrLooseMessage;
    int val1, val2, goodAnswer, wrongAnswer;
    Button button;
    String result;
    GridLayout gridLayout;
    Button buttonGrid;

    public void randomButtonGridLayout(){
        Random tagButton = new Random();
        int whichButtonResult = tagButton.nextInt(3);

        for(int i=0; i< gridLayout.getChildCount(); i++){
            int randomButtons = tagButton.nextInt(40);
            int tagChild = Integer.parseInt(gridLayout.getChildAt(i).getTag().toString());
            buttonGrid = (Button)gridLayout.getChildAt(i);
            buttonGrid.setText(String.valueOf(randomButtons));
            if (whichButtonResult == tagChild ) {
                Log.i("Information Button","tag value => "+tagChild);
                buttonGrid.setText(String.valueOf(result));
            }
        }
    }

    public void randomResult(){
        Random random = new Random();
        val1 = random.nextInt(20);
        val2 = random.nextInt(20);
        result = String.valueOf(val1 + val2);
        postSum.setText(String.valueOf(val1)+" + "+val2);

    }

    public void playAgain(View view){
        button = (Button) view;
        for(int i=0; i< gridLayout.getChildCount(); i++){
            button = (Button)gridLayout.getChildAt(i);
            button.setEnabled(true);
        }
        buttonPlayAgain.setVisibility(View.INVISIBLE);
        winOrLooseMessage.setVisibility(View.INVISIBLE);
        goodAnswer=0;
        wrongAnswer=0;
        winOrLooseButton.setText(goodAnswer+"/"+wrongAnswer);
        timer(timerButton);
        randomResult();
        randomButtonGridLayout();
    }
    public void timer(View view){
         timerButton = (Button) view;
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {

                timerButton.setText(l/1000 + "s");
            }

            @Override
            public void onFinish() {
                timerButton.setText("0");
                buttonPlayAgain.setVisibility(View.VISIBLE);
                winOrLoose.setVisibility(View.INVISIBLE);
                winOrLooseMessage.setVisibility(View.VISIBLE);
                String message = "";
                if (goodAnswer > wrongAnswer) {
                    message = "YOU WIN (^_^) !";
                } else if (goodAnswer < wrongAnswer){
                    message = "YOU Loose :(";
                } else {
                    message = "No win, No Loose !";
                }
                winOrLooseMessage.setText(message);
                Button buttonX;
                for(int i=0; i< gridLayout.getChildCount(); i++){
                    buttonX = (Button)gridLayout.getChildAt(i);
                    buttonX.setEnabled(false);
                }
            }
        }.start();
    }

    public void gridLayoutButton (View view){
         button = (Button) view;
        if (button.getText().toString() == result){
            winOrLoose.setText("Correct !");
            winOrLoose.setVisibility(View.VISIBLE);
            goodAnswer+=1;
            winOrLooseButton.setText(goodAnswer+"/"+wrongAnswer);
        }else {
            winOrLoose.setText("Wrong :(");
            winOrLoose.setVisibility(View.VISIBLE);
            wrongAnswer+=1;
            winOrLooseButton.setText(goodAnswer+"/"+wrongAnswer);

        }
        randomResult();
        randomButtonGridLayout();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize objects
        timerButton = (Button) findViewById(R.id.timerButton);
        postSum = (TextView) findViewById(R.id.sumTextView);
        winOrLoose = (TextView) findViewById(R.id.winOrLooseTextView);
        winOrLooseMessage = (TextView) findViewById(R.id.winOrLooseMessage);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        winOrLooseButton = (Button) findViewById(R.id.winOrLooseButton);
        buttonPlayAgain = (Button) findViewById(R.id.playAgain);
        goodAnswer = 0;
        wrongAnswer = 0;

        winOrLoose.setVisibility(View.INVISIBLE);
        buttonPlayAgain.setVisibility(View.INVISIBLE);
        timer(timerButton);
        randomResult();
        randomButtonGridLayout();

    }
}