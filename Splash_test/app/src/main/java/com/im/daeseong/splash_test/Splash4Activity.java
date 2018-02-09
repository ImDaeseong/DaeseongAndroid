package com.im.daeseong.splash_test;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Splash4Activity extends AppCompatActivity {

    private static final String TAG = Splash4Activity.class.getSimpleName();

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash4);

        textView1 = (TextView)findViewById(R.id.textView1);

        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                textView1.setText("" +millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textView1.setText("끝");

                startActivity(new Intent(Splash4Activity.this, MainActivity.class));
            }
        }.start();

    }
}
