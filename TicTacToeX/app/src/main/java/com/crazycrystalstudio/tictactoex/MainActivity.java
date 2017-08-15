package com.crazycrystalstudio.tictactoex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //o - circle 1 - cross
    int activePlayer = 0;
    boolean isGameActive = true;

    int[] gameStates = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][]  winCondition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("TicTacToe");

        setSupportActionBar(toolbar);

    }

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int taggedCounter =  Integer.parseInt(counter.getTag().toString());
        if(gameStates[taggedCounter] == 2 && isGameActive)
        {
            gameStates[taggedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.circle_0);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross_1);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            //check anyone is winning
            for(int[] winCond : winCondition){
                if(gameStates[winCond[0]] != 2
                        && gameStates[winCond[0]] == gameStates[winCond[1]]
                        && gameStates[winCond[0]] == gameStates[winCond[2]]){
                    System.out.print("Won: "+gameStates[winCond[0]]);

                    isGameActive = false;

                    TextView tv = (TextView) findViewById(R.id.winMessage);
                    if(gameStates[winCond[0]] == 0)
                        tv.setText("CIRCLE WON");
                    else
                        tv.setText("CROSS WON");


                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainPopup);
                    layout.setVisibility(View.VISIBLE);
                }else{
                    System.out.println("in else part");
                    boolean isGameOver = true;
                    for(int i : gameStates){
                        if(i == 2)
                            isGameOver = false;
                    }

                    if(isGameOver){
                        TextView tv = (TextView) findViewById(R.id.winMessage);

                        tv.setText("GAME IS DRAW");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainPopup);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){

        isGameActive = true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainPopup);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i=0; i<gameStates.length; i++){
            gameStates[i] = 2;
        }

        GridLayout glayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<glayout.getChildCount(); i++){
            ((ImageView) glayout.getChildAt(i)).setImageResource(0);
        }

    }

}
