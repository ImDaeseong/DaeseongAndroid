package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import java.util.ArrayList;

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

        ArrayList<BubbleEntry> bubblelist = new ArrayList<>();
        bubblelist.add(new BubbleEntry(0, 1, 10f));
        bubblelist.add(new BubbleEntry(1, 3, 20f));
        bubblelist.add(new BubbleEntry(2, 2, 15f));
        bubblelist.add(new BubbleEntry(3, 4, 30f));

        BubbleDataSet dataSet = new BubbleDataSet(bubblelist, "BubbleChart");
        dataSet.setColor(Color.GRAY);
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.WHITE);

        BubbleData data = new BubbleData(dataSet);
        bubbleChart.setData(data);

        //차트 업데이트
        bubbleChart.invalidate();
    }
}