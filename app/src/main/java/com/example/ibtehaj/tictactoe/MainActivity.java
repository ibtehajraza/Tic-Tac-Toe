package com.example.ibtehaj.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean active = true;
    //1=>O  0=>x
    int activePlayer = 0;

    // 2 => no state is set
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropInMid(View view){

        ImageView counter = (ImageView) view;
//        counter.setTranslationY(-1000f);

//        counter.animate().translationYBy(1000f).setDuration(300);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && active) {
            counter.setAlpha(0f);
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            }

            else {

                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            counter.animate().alpha(1f).setDuration(1500);
        }

        for(int[] winningPos : winningPosition){
            if(gameState[winningPos[0]] == gameState[winningPos[1]]
                    && gameState[winningPos[1]] == gameState[winningPos[2]]
                    && gameState[winningPos[0]] != 2)
            {
                active=false;

                Log.i("key","winner"+gameState[winningPos[0]]);

                TextView winner = (TextView) findViewById(R.id.winner);
                String  win = "PLAYER 2";

                if(gameState[winningPos[0]] == 0) {
                    win = "PLAYER 1";
                }
                winner.setText(win + " has WON ");
                final LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);
                layout.setVisibility(View.VISIBLE);

            }

        }
        int j=0;
        for(int i=0 ; i<gameState.length;i++){
            if(gameState[i] != 2){
                j++;
            }
        }
        Log.i("key","j= "+j);

        if(j == 9){
            drawGame();
        }

    }

    public void drawGame(){
        TextView winner = (TextView) findViewById(R.id.winner);
        String t="Game Draw!";
        winner.setText(t);

        final LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);
        layout.setVisibility(View.VISIBLE);
    }


    public void resetGame (View view){
        final LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);
        layout.setVisibility(View.INVISIBLE);

        active=true;

        activePlayer = 0;
        for(int i= 0 ; i < gameState.length ; i++){
            gameState[i]=2;
        }

        GridLayout boardGrid = (GridLayout) findViewById(R.id.boardGrid);

        for(int i=0 ; i<boardGrid.getChildCount();i++){
            ((ImageView) boardGrid.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
