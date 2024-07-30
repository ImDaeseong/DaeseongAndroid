package com.im.daeseong.timer_test;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Timer3Activity extends AppCompatActivity {

    private TextView tv1;
    private Button btn1;

    private Handler handler;
    private Runnable runnable;
    private long startTime;
    private boolean isRunning = false;

    private static final long UPDATE_INTERVAL = 1000; // 1초

    private void startTimer() {

        if (isRunning) return;

        isRunning = true;
        startTime = System.currentTimeMillis();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                long elapsedMillis = System.currentTimeMillis() - startTime;
                long secondsElapsed = elapsedMillis / 1000;

                tv1.setText("타이머: " + secondsElapsed + " 초 경과");

                // 1초마다 반복
                handler.postDelayed(this, UPDATE_INTERVAL);
            }
        };

        handler.post(runnable);
    }

    private void stopTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable);
            isRunning = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer3);

        tv1 = findViewById(R.id.tv1);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}