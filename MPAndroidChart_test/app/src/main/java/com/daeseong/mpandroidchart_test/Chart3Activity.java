package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.CandleStickChart;

public class Chart3Activity extends AppCompatActivity {

    private static final String TAG = Chart3Activity.class.getSimpleName();

    private CandleStickChart candleStickChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart3);

        candleStickChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}