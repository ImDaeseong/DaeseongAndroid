package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BubbleChart;

public class Chart2Activity extends AppCompatActivity {

    private static final String TAG = Chart2Activity.class.getSimpleName();

    private BubbleChart bubbleChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart2);

        bubbleChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}