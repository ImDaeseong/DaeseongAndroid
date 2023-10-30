package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;

public class Chart7Activity extends AppCompatActivity {

    private static final String TAG = Chart7Activity.class.getSimpleName();

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart7);

        pieChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

    }
}