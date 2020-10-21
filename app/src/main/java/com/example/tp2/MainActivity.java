package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    Button startBtn, stopBtn, R_Init_Btn;
    TextView countDownView;
    public CountdownModel countdownModel = new CountdownModel();
    public Handler myHandler = new Handler();
    public  Runnable myRunnableS;
    public int startValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.StartButton);
        stopBtn = findViewById(R.id.StopButton);
        R_Init_Btn = findViewById(R.id.ReInitilizeButton);

         countDownView = findViewById(R.id.CountDown);
        countDownView.setText(countdownModel.getInit_val()+"");

        countdownModel.setStartValue(60);
        startValue =  countdownModel.getStartValue();

        myRunnableS =  new Runnable() {
            @Override
            public void run() {

                countDownView.setText(startValue + "");
                startValue--;
                if(startValue >= 0){
                    myHandler.postDelayed(this, 1000);

                }else if(startValue<0){
                    Toast.makeText(getBaseContext(), "CountDown Atteinte ", Toast.LENGTH_LONG).show();
                    R_Init_Btn.setEnabled(true);
                }
            }
        };

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "CountDown Launched", Toast.LENGTH_LONG).show();
                myHandler.post(myRunnableS);
                countDownView.setText(countdownModel.getStartValue()+"");
              //  R_Init_Btn.setEnabled(false);
                R_Init_Btn.setEnabled(false);
                stopBtn.setEnabled(true);
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "CountDown Stoped", Toast.LENGTH_LONG).show();
                myHandler.removeCallbacks(myRunnableS);
                startBtn.setEnabled(true);
                R_Init_Btn.setEnabled(true);
            }
        });
        R_Init_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startValue = countdownModel.getStartValue();
                countDownView.setText(countdownModel.getStartValue()+"");
                Toast.makeText(getBaseContext(), "CountDown Re_Initlized", Toast.LENGTH_LONG).show();
                stopBtn.setEnabled(false);
                startBtn.setEnabled(true);

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        myHandler.removeCallbacks(myRunnableS);
        Toast.makeText(getBaseContext(), "You are Outside the App, CountDown Would be Stopped", Toast.LENGTH_LONG).show();
    }



}