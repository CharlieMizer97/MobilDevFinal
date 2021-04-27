package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class tictactoe extends AppCompatActivity {

    boolean gameActive = true;

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};//basically its nulled at the moment

    final int[][] winPositions = {{0, 1, 2},
                            {3, 4, 5}, {6, 7, 8},
                            {0, 3, 6}, {1, 4, 7},
                            {2, 5, 8},{0, 4, 8},
                            {2, 4, 6}};
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
    }

    @SuppressLint("SetTextI18n")
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int userChoice = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            gameReset(view);
        }

        if (gameState[userChoice] == 2) {
            counter++;

            if (counter == 9) {
                gameActive = false;
            }

            gameState[userChoice] = activePlayer;

            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                ((TextView) findViewById(R.id.status)).setText("O's");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                ((TextView) findViewById(R.id.status)).setText("X's");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                gameActive = false;

            }
        }
        if (counter == 9 && flag == 0) {
            TextView status = findViewById(R.id.status);
            status.setText("Match");
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);

        for (int i = 0; i < 9; i++){

            ((ImageView) findViewById(R.id.imageView + i)).setImageResource(0);

        }
    }
}