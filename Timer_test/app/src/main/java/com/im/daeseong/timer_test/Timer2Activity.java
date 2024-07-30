package com.im.daeseong.timer_test;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Timer2Activity extends AppCompatActivity {

    private TextView tv1;
    private Button btn1;


    private CountDownTimer timer;
    private static final long TIMER_DURATION = 60000; // 1분

    private void startTimer() {

        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(TIMER_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long secondsRemaining = millisUntilFinished / 1000;
                tv1.setText("타이머: " + secondsRemaining + " 초 남음");
            }

            @Override
            public void onFinish() {
                tv1.setText("타이머 완료");
            }
        }.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer2);

        tv1 = (TextView)findViewById(R.id.tv1);
        btn1 = (Button)findViewById(R.id.btn1);
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