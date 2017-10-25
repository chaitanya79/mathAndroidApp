package com.gabrielunesi.app1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;


public class ScoreView extends AppCompatActivity {

    TextView easyScore,mediumScore,hardScore;
    int score, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);
        easyScore = (TextView) findViewById(R.id.easyScore);
        mediumScore = (TextView) findViewById(R.id.mediumScore);
        hardScore = (TextView) findViewById(R.id.hardScore);

        final ArrayList list = getIntent().getIntegerArrayListExtra("scorelist");
        score = (int) list.get(1);
        difficulty = (int) list.get(0);
        if (difficulty == 0) {
                easyScore.setText("Easy: your score is: " + score);
                mediumScore.setText("Medium: not played!");
                hardScore.setText("Hard: not played!");
        }
        else if (difficulty == 1 && score >= 3) {
            mediumScore.setText("Medium: Won!");
            easyScore.setText("Easy: Won!");
            hardScore.setText("Hard: not played!");
        }
        else if (difficulty == 1) {
            mediumScore.setText("Medium: your score is: " + score);
            easyScore.setText("Easy: Won!");
            hardScore.setText("Hard: not played!");

        }
        else if (difficulty == 2 && score >= 3) {
            easyScore.setText("Easy: Won!");
            mediumScore.setText("Medium: Won!");
            hardScore.setText("Hard: Won!");
        }
        else if (difficulty == 2) {
            easyScore.setText("Easy: Won!");
            mediumScore.setText("Medium: Won!");
            hardScore.setText("Hard: your score is: " + score);
        }
    }
}
