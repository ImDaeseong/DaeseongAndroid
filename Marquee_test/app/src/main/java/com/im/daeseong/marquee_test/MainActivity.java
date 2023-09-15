package com.im.daeseong.marquee_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;

    private MarqueeTask task;
    private Timer timer = null;

    private void closeTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void startTimer(){
        closeTimer();
        task = new MarqueeTask(tv1);
        timer = new Timer();
        timer.schedule(task, 0, 10000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);

        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        closeTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        closeTimer();
    }

}
