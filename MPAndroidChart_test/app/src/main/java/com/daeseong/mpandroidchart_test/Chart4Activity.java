package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.CombinedChart;

public class Chart4Activity extends AppCompatActivity {

    private static final String TAG = Chart4Activity.class.getSimpleName();

    private CombinedChart combinedChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart4);

        combinedChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}