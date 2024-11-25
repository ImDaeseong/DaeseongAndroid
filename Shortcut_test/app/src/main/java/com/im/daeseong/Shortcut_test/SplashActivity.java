package com.im.daeseong.Shortcut_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String sPackageName = getIntent().getStringExtra("pkg");
        String sID = getIntent().getStringExtra("userId");
        String sUserData = getIntent().getStringExtra("userData");

        if (sPackageName != null && !sPackageName.isEmpty()) {
            Log.e(TAG, "sPackageName:" + sPackageName);
            Toast.makeText(this, sPackageName, Toast.LENGTH_LONG).show();
        }

        if (sID != null && !sID.isEmpty()) {
            Log.e(TAG, "sID:" + sID);
            Toast.makeText(this, sID, Toast.LENGTH_LONG).show();
        }

        if (sUserData != null && !sUserData.isEmpty()) {
            Log.e(TAG, "sUserData:" + sUserData);
            Toast.makeText(this, sUserData, Toast.LENGTH_LONG).show();
        }

        init();
    }

    private void init() {

        // 상태바 숨기기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // MainActivity 이동
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}