package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    // 0: Yellow;   1: Red;   2: empty 

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;
    List<ImageView> allImageViews;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        TextView winnerOutput = (TextView)findViewById(R.id.winnerTextView);
        Button buttonReplay = (Button)findViewById(R.id.replayButton);
        int taggedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[taggedCounter] == 2 && gameActive) {
            counter.setTranslationY(-1500);
            gameState[taggedCounter] = activePlayer;
            allImageViews.add(counter);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){
                   String winner = "";
                   if (activePlayer == 1){
                       winner = "Yellow";
                   } else {
                       winner = "Red";
                   }
                    gameActive = false;
                    Toast.makeText(this, winner+" has won", Toast.LENGTH_SHORT).show();
                    buttonReplay.animate().alpha(1);
                    winnerOutput.setText(winner+" has won");
                    winnerOutput.animate().alpha(1);
                    Log.i("ImageViews","Total imageViews: "+allImageViews.size());
                }
            }
        }
    }
    public void replay(View view){
        Button buttonReplay = (Button)findViewById(R.id.replayButton);
        TextView winnerOutput = (TextView)findViewById(R.id.winnerTextView);
        for (int i=0; i<allImageViews.size();i++){
            allImageViews.get(i).setImageResource(0);
        }
        /* -- special loop into a gridLayout
            GridLayout gridLayout = (GridLayout) findViewById(R.id.my_grid);

            for (int i=0;i<gridLayout.getChildCount();i++){

                ImageView counterViews = (ImageView) gridLayout.getChildAt(i);
                allImageViews.get(i).setImageDrawable(null);
            }
         */

        buttonReplay.animate().alpha(0).setDuration(2000);
        winnerOutput.animate().alpha(0).setDuration(2000);
        gameActive = true;
        allImageViews.clear();
        int[] initGameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        for (int i=0; i<initGameState.length;i++){
            gameState[i] = initGameState[i];
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView winnerOutput = (TextView)findViewById(R.id.winnerTextView);
        Button buttonReplay = (Button)findViewById(R.id.replayButton);
        buttonReplay.animate().alpha(0);
        winnerOutput.animate().alpha(0);
        allImageViews = new ArrayList<ImageView>();
    }
}