package com.im.daeseong.splash_test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.im.daeseong.splash_test.util.SharedPreferences_util;

import java.util.Timer;
import java.util.TimerTask;

public class Splash1Activity extends AppCompatActivity {

    private static final String TAG = Splash1Activity.class.getSimpleName();

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash1);

        //first
        timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                boolean isFirst = (boolean)SharedPreferences_util.getValue(Splash1Activity.this, "FRIST", false);
                if(isFirst){
                    Log.e(TAG, "Second");
                }else {
                    Log.e(TAG, "First");
                    SharedPreferences_util.setValue(Splash1Activity.this, "FRIST", true);
                }
                finish();
            }
        };
        timer.schedule(task, 1000);


        //second
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean isFirst = (boolean)SharedPreferences_util.getValue(Splash1Activity.this, "FRIST", false);
                if(isFirst){
                    Log.e(TAG, "Second");
                }else {
                    Log.e(TAG, "First");
                    SharedPreferences_util.setValue(Splash1Activity.this, "FRIST", true);
                }
                finish();

            }
        }, 1000);
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try{
            if(timer != null){
                timer.cancel();
                timer = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
