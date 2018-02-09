package com.im.daeseong.splash_test;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Splash2Activity extends AppCompatActivity {

    private static final String TAG = Splash2Activity.class.getSimpleName();

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                Log.e(TAG, "run");
                dismissSplah();
            }
        };

        //액티비티 메인 화면 클릭시 인식
        View rootView = findViewById(android.R.id.content);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "onClick");
                dismissSplah();
            }
        });
    }

    private void dismissSplah(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");
        handler.postDelayed(runnable, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(TAG, "onPause");
        handler.removeCallbacks(runnable);
    }
}
