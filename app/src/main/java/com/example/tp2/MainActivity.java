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

public class MainActivity extends AppCompatActivity {
    Button startBtn, stopBtn, R_Init_Btn;
    TextView cdView;
    public CountdownModel countdownModel = new CountdownModel();
    public Handler myHandler = new Handler();
    public  Runnable myRunnable;
    public int startValue , ctest=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.StartButton);
        stopBtn = findViewById(R.id.StopButton);
        R_Init_Btn = findViewById(R.id.ReInitilizeButton);

         cdView = findViewById(R.id.CountDown);
         cdView.setText(countdownModel.getInit_val()+"");

         countdownModel.setStartValue(60);
         startValue = countdownModel.getStartValue();

        myRunnable =  new Runnable() {
            @Override
            public void run() {
                cdView.setText(startValue + "");
                startValue--;
                if(startValue<0){
                    myHandler.postDelayed(this, 1000);
                }else{
                    Toast.makeText(getBaseContext(), "CountDown Atteinte ", Toast.LENGTH_LONG).show();
                }

            }
        };

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHandler.post(myRunnable);
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHandler.removeCallbacks(myRunnable);
            }
        });
        R_Init_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cdView.setText(countdownModel.getStartValue()+"");
            }
        });

    }


    public void startCountdown1(){

        //long remainingTime = countdownModel.getRemainingTime(Integer.valueOf(cdView.getText().toString()));
        //Toast.makeText(getBaseContext(), ""+remainingTime, Toast.LENGTH_LONG).show();
        // cdView.setText(remainingTime +"");
        long startValue = countdownModel.getStartValue() * 1000;
                new CountDownTimer(startValue, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                     //   millisUntilFinished = countdownModel.getStartTime();
                        cdView.setText(millisUntilFinished/1000+"");
                    }

                    @Override
                    public void onFinish() {
                            cancel();
                    }
                }.start();
    }

}