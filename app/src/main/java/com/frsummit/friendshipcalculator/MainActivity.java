package com.frsummit.friendshipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculationActivityStart();
    }

    private void calculationActivityStart() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Calculation.class);
                startActivity(intent);
                finishscreen();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }

    private void finishscreen() {
        this.finish();
    }
}
