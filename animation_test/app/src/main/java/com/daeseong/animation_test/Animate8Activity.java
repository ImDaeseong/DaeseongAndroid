package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;

public class Animate8Activity extends AppCompatActivity {

    private static final String TAG = Animate8Activity.class.getSimpleName();

    private Button button1;
    private ImageView image1;

    private spriteTask spriteTask;
    private Timer timer = null;

    private void closeTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void startTimer(){
        closeTimer();
        spriteTask = new spriteTask(this, image1);
        timer = new Timer();
        timer.schedule(spriteTask, 0, 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate8);

        image1 = (ImageView)findViewById(R.id.image1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startTimer();
            }
        });
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
