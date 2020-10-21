package com.example.tp2;

import android.os.SystemClock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class CountdownModel implements Serializable {
    public final int init_val = 00;
    public int startValue;
    long startTime = SystemClock.elapsedRealtime();
    long estimatedTime = System.nanoTime() - startTime;

    public long getStartTime() {
        long startTimeConverted = TimeUnit.MICROSECONDS.toSeconds(startTime);
        return startTimeConverted;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public CountdownModel() {

    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;

    }

    public int getInit_val() {
        return init_val;
    }

    public long getRemainingTime( long countDownAtMoment){
        long timeRemaining;
        long countDownStart = Long.valueOf(getStartValue());
            timeRemaining = countDownStart - countDownAtMoment;
        return timeRemaining;
    }

}
