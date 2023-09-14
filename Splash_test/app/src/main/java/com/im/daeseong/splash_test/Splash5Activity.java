package com.im.daeseong.splash_test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash5Activity extends AppCompatActivity {

    private static final String TAG = Splash5Activity.class.getSimpleName();

    private ImageView imageView1;
    private Animation FadeInScale;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_splash5);

        StartAnimation();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(Splash5Activity.this, MainActivity.class));
            }
        }, 3000);
    }

    private void StartAnimation(){

        Log.e(TAG, "StartAnimation");

        imageView1 = (ImageView)findViewById(R.id.imageView1);

        FadeInScale = AnimationUtils.loadAnimation(Splash5Activity.this, R.anim.fade_in_scale);
        FadeInScale.setFillAfter(true);

        imageView1.startAnimation(FadeInScale);
    }
}
