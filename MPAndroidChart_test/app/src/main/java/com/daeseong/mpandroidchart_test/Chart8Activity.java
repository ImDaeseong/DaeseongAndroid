package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.RadarChart;

public class Chart8Activity extends AppCompatActivity {

    private static final String TAG = Chart8Activity.class.getSimpleName();

    private RadarChart radarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart8);

        radarChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}