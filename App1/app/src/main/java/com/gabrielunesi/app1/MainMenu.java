package com.gabrielunesi.app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {


    Button StartGameButton;
    Button ScoreButton;
    Button HelpButton;
    Button easyButton, mediumButton, hardButton;
    int difficulty, score, completeMedium, completeEasy;
    TextView complDiff;
    ArrayList<Integer> scorelist = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        scorelist.add(0);
        scorelist.add(0);
        StartGameButton = (Button) findViewById(R.id.StartGameButton);
        ScoreButton = (Button) findViewById(R.id.ScoreButton);
        HelpButton = (Button) findViewById(R.id.HelpButton);
        easyButton = (Button)findViewById(R.id.easyButton);
        mediumButton = (Button)findViewById(R.id.mediumButton);
        hardButton = (Button)findViewById(R.id.hardButton);
        complDiff = (TextView)findViewById(R.id.completeDiff);


        //Choose easy setting difficult parameter to 0
        easyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                difficulty = 0;
                complDiff.setText("Easy choosen");
            }
        });


        //Choose medium setting difficult parameter to 1, if easy setting is completed
        mediumButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(completeEasy > 0){
                    difficulty = 1;
                    complDiff.setText("Medium choosen");
                }
                else{
                    complDiff.setText("Complete Easy first!");
                }
            }
        });
        //Choose hard setting difficult parameter to 2, if medium setting is completed
        hardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(completeMedium > 0) {
                    difficulty = 2;
                    complDiff.setText("Hard choosen");
                }
                else{
                    complDiff.setText("Complete Medium first!");
                }
            }
        });

        //Start GameView acitivity with intent. Also sends difficulty data.
        StartGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent imageIntent = new Intent(getBaseContext(), GameView.class);
                imageIntent.putExtra("key",difficulty);
                startActivityForResult(imageIntent,0);
            }
        });

        //Button to show score. Takes in score and difficulty data
        ScoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent imageIntent = new Intent(getBaseContext(), ScoreView.class);
                if(difficulty==0) {
                    imageIntent.putIntegerArrayListExtra("scorelist", scorelist);

                }
                else if(difficulty==1){
                    imageIntent.putIntegerArrayListExtra("scorelist", scorelist);
                }
                else if(difficulty==2){
                    imageIntent.putIntegerArrayListExtra("scorelist",scorelist);
                }
                //imageIntent.putExtra("easyscore",easyscore);
                startActivityForResult(imageIntent,0);
            }
        });

        HelpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent imageIntent = new Intent(MainMenu.this, HelpView.class);
                startActivity(imageIntent);
            }
        });

    }
    //Gets score data from game view
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                if(difficulty == 0) {
                    score = data.getIntExtra("score", 0);
                    scorelist.set(0,0);
                    scorelist.set(1,score);
                }
                else if(difficulty==1){
                    score = data.getIntExtra("score", 0);
                    scorelist.set(0,1);
                    scorelist.set(1,score);
                }
                else if(difficulty == 2){
                    score = data.getIntExtra("score",0);
                    scorelist.set(0,2);
                    scorelist.set(1,score);
                }
            }
        }
        if((scorelist.get(1) >= 3) && (scorelist.get(0) == 0)){
            completeEasy++;
        }
        if((scorelist.get(1) >= 3) && (scorelist.get(0) == 1)){
            completeMedium++;
        }
    }

}
