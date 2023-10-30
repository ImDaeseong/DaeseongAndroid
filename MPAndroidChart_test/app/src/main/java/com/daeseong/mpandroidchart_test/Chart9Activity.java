package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.ScatterChart;

public class Chart9Activity extends AppCompatActivity {

    private static final String TAG = Chart9Activity.class.getSimpleName();

    private ScatterChart scatterChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart9);

        scatterChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}