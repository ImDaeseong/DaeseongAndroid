package com.daeseong.control_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private ProgressBar progress_circular;
    private TextView tv1;
    private double dTotalHour, dTotalMinute;
    private double dMyHour, dMyMinute;
    private double dResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.tv1);

        initProgressCircular();

        CalcuPercent();
    }

    private void initProgressCircular() {
        progress_circular = findViewById(R.id.progress_circular);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        progress_circular.setProgress(0);   // Main Progress
        progress_circular.setSecondaryProgress(100); // Secondary Progress
        progress_circular.setMax(100); // Maximum Progress
        progress_circular.setProgressDrawable(drawable);
    }

    private void CalcuPercent(){

        dTotalHour = 5 * 60; //5시간 * 60 분
        dTotalMinute = 30; //30분

        dMyHour = 1* 60; //1시간 * 60 분
        dMyMinute = 30; //30분

        dResult = ((dMyHour + dMyMinute) / (dTotalHour + dTotalMinute) ) * 100;
        //Log.d(TAG, Double.toString(dResult));
        int nValue = (int)dResult;

        tv1.setText(Integer.toString(nValue) + "%");
        progress_circular.setProgress(nValue);
    }
}
