package com.daeseong.progresscircular_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressBar progress_circular;
    private TextView tv1;
    private double dTotalHour, dTotalMinute;
    private double dMyHour, dMyMinute;
    private double dResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){

        tv1 = findViewById(R.id.tv1);

        //프로그래스 초기화
        initProgressCircular();

        //프로그래스 표시
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

    //프로그래스 표시
    private void CalcuPercent(){

        //전체 시간중 현재 소요 시간에 대한 평균

        /*
        dTotalHour = 5 * 60; //5시간 * 60 분
        dTotalMinute = 30; //30분

        dMyHour = 1* 60; //1시간 * 60 분
        dMyMinute = 30; //30분

        dResult = ((dMyHour + dMyMinute) / (dTotalHour + dTotalMinute) ) * 100;
        //Log.d(TAG, Double.toString(dResult));
        int nValue = (int)dResult;

        tv1.setText(Integer.toString(nValue) + "%");
        progress_circular.setProgress(nValue);
        */

        //총 5시간 30분
        dTotalHour = 5 * 60; //5시간 * 60 분
        dTotalMinute = 30; //30분

        //현재 4시간 50분 소요
        dMyHour = 4* 60; //4시간 * 60 분
        dMyMinute = 50; //50분

        dResult = ((dMyHour + dMyMinute) / (dTotalHour + dTotalMinute) ) * 100;
        //Log.d(TAG, Double.toString(dResult));
        int nValue = (int)dResult;

        tv1.setText(Integer.toString(nValue) + "%");
        progress_circular.setProgress(nValue);
    }

}
