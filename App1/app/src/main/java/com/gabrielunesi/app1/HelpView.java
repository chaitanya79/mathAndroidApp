package com.gabrielunesi.app1;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HelpView extends AppCompatActivity {

    TextView modellName, infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_view);
        modellName = (TextView)findViewById(R.id.modellname);
        infoText = (TextView)findViewById(R.id.infoText);


        String model = Build.MODEL;

        String reqString = Build.MANUFACTURER
                + " " + Build.VERSION.RELEASE
                + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();

        modellName.setText("Your Android version is: " + reqString);
        infoText.setText("To win this game you have to answer correct in at least three questions for each level. After you complete and succesfully complete three questions you can advance the next difficulty level. The game has three different difficulty modes.");
    }
}
