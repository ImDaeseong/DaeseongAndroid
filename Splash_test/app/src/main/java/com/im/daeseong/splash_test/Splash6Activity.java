package com.im.daeseong.splash_test;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Splash6Activity extends AppCompatActivity {

    private Timer timer;

    private ImageView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash6);

        banner = (ImageView)findViewById(R.id.banner);

        Random random = new Random();
        int i = random.nextInt(4);
        switch (i){
            case 0:
                banner.setImageResource(R.drawable.number1);
                break;
            case 1:
                banner.setImageResource(R.drawable.number2);
                break;
            case 2:
                banner.setImageResource(R.drawable.number3);
                break;
            case 3:
                banner.setImageResource(R.drawable.number4);
                break;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Splash6Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Splash6Activity.this, MainActivity.class);
                        Splash6Activity.this.startActivity(intent);
                        finish();
                    }
                });
            }
        }, 5000);

    }
}
