package com.bignerdranch.android.logicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int currentColor;
    int guessColor;
    int scoreYes = 0;
    int scoreNo = 0;
    int amount = 1;
    int interval = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textHeader = findViewById(R.id.header);
        textHeader.setText("Чи співпадає назва кольору зліва з кольором техта зправа?");
//        run();
    }

    public void onRadioClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.minTime:
                if(checked){
                    amount = 30000;
                    run();
                }
                break;

            case R.id.maxTime:
                if(checked){
                    amount = 60000;
                    interval = 2000;
                    run();
                }
                break;
        }

    }

    public void guessYes(View view){
        if (guessColor == currentColor){
            scoreYes++;
        }
    }
    public void displayResult(String result){
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }

    public void run(){
        TextView textViewRight = findViewById(R.id.textViewRight);
        TextView textViewLeft = findViewById(R.id.textViewLeft);


            Random rand = new Random();
            currentColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            textViewLeft.setBackgroundColor(currentColor);

            new CountDownTimer(amount,interval){
                public void onTick(long millisUntilFinished){
                    guessColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                    textViewRight.setBackgroundColor(guessColor);
                }
                public void onFinish(){
                    displayResult("All done: ");

                }
            }.start();

    }

    public void guessNo(View view){
        if (guessColor != currentColor){
            scoreNo++;
        }
    }
}