package com.im.daeseong.progressbar_test;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class Main5Activity extends AppCompatActivity {

    private ProgressBar pb1;
    private int progress = 0;
    private int max = 10000;
    private int pos = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        pb1 = (ProgressBar)findViewById(R.id.pb1);

        loadprogress();
    }

    private void loadprogress(){

        CountDownTimer countDownTimer;
        pb1.setMax((int) max);
        pb1.setProgress(0);

        countDownTimer = new CountDownTimer(max, pos) {
            @Override
            public void onTick(long l) {
                progress += pos;
                pb1.setProgress(progress);
            }

            @Override
            public void onFinish() {
                progress += pos;
                pb1.setProgress(progress);
                pb1.setVisibility(View.INVISIBLE);
            }
        };
        countDownTimer.start();
    }

}
