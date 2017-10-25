package com.gabrielunesi.app1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

import static com.gabrielunesi.app1.R.id.confirmButton;
import static com.gabrielunesi.app1.R.string.difficulty;

public class GameView extends AppCompatActivity {

    TextView textScore;
    EditText Answer;
    Button btnAdd, backButton;
    double inputValue;
    int score,Fails, musicOnOff;
    int firstValue, secondValue;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.zapsplat_cartoon_heavy_marching_elephants_musical_horns_13105);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        backButton = (Button) findViewById(R.id.backButton);
        textScore = (TextView) findViewById(R.id.textScore);
        final TextView MathQuestion = (TextView) findViewById(R.id.MathQuestion);
        btnAdd = (Button) findViewById(confirmButton);
        Answer = (EditText) findViewById(R.id.Answer);

        final int s = getIntent().getIntExtra("key", difficulty);
        final Random generator = new Random();

        //Turn off and on music
        ToggleButton chooseDifficulty = (ToggleButton) findViewById(R.id.musicSwitch);
        chooseDifficulty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mediaPlayer.stop();
                }
                else{
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.zapsplat_cartoon_heavy_marching_elephants_musical_horns_13105);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            }
        });

        //Back to main view, also sends back score data.
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent intent = new Intent();
                if (s == 0) {
                    intent.putExtra("score", score);
                } else if (s == 1) {
                    intent.putExtra("score", score);
                } else if (s == 2) {
                    intent.putExtra("score", score);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //Based on difficulty, makes the firstValue and secondValue and creates the question
        if (s == 1) {
            firstValue = generator.nextInt(100) + 1;
            secondValue = generator.nextInt(100) + 1;
        } else if (s == 0) {
            firstValue = generator.nextInt(10) + 1;
            secondValue = generator.nextInt(10) + 1;
        } else if (s == 2) {
            firstValue = generator.nextInt(1000) + 1;
            secondValue = generator.nextInt(1000) + 1;
        }
        MathQuestion.setText("What is: " + firstValue + " + " + secondValue + "  ?");
        textScore.setText("Your score is: " + score);

        //Button to compare answer with sumbitted answer to see if it is correct or incorrect
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValue = Double.parseDouble(Answer.getText().toString());
                if (firstValue + secondValue == inputValue) {
                    score++;
                    textScore.setText("Correct! Your score is: " + score);
                    if (s == 1) {
                        firstValue = generator.nextInt(100) + 1;
                        secondValue = generator.nextInt(100) + 1;
                    } else if (s == 0) {
                        firstValue = generator.nextInt(10) + 1;
                        secondValue = generator.nextInt(10) + 1;
                    } else if (s == 2) {
                        firstValue = generator.nextInt(1000) + 1;
                        secondValue = generator.nextInt(1000) + 1;
                    }
                    MathQuestion.setText("What is: " + firstValue + " + " + secondValue + "  ?");
                } else {
                    Fails++;
                    textScore.setText("False!\nCorrect answer was: " + (firstValue + secondValue) + ".\n" + "Your score is: " + score);
                    if (s == 1) {
                        firstValue = generator.nextInt(100) + 1;
                        secondValue = generator.nextInt(100) + 1;
                    } else if (s == 0) {
                        firstValue = generator.nextInt(10) + 1;
                        secondValue = generator.nextInt(10) + 1;
                    } else if (s == 2) {
                        firstValue = generator.nextInt(1000) + 1;
                        secondValue = generator.nextInt(1000) + 1;
                    }
                    MathQuestion.setText("What is: " + firstValue + " + " + secondValue + "  ?");
                }
            }
        });
    }
}
