package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.HorizontalBarChart;

public class Chart5Activity extends AppCompatActivity {

    private static final String TAG = Chart5Activity.class.getSimpleName();

    private HorizontalBarChart horizontalBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart5);

        horizontalBarChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}