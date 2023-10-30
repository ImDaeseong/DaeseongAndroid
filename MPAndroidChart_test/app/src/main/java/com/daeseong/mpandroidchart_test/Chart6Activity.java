package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;

public class Chart6Activity extends AppCompatActivity {

    private static final String TAG = Chart6Activity.class.getSimpleName();

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart6);

        lineChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}