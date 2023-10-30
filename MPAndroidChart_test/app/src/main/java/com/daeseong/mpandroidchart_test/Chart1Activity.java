package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.Arrays;

public class Chart1Activity extends AppCompatActivity {

    private static final String TAG = Chart1Activity.class.getSimpleName();

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart1);

        barChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        // 데이터 포인트 생성
        BarEntry[] entries = new BarEntry[]{
                new BarEntry(1, 30f),
                new BarEntry(2, 10f),
                new BarEntry(3, 60f),
                new BarEntry(4, 20f),
                new BarEntry(5, 40f)
        };

        BarDataSet dataSet = new BarDataSet(Arrays.asList(entries), "barChart");
        dataSet.setColor(Color.GRAY);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        //하단 레이블
        String[] labels = new String[]{"Label1", "Label2", "Label3", "Label4", "Label5"};
        barChart.getXAxis().setValueFormatter(new MyXAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setAxisMinimum(0);

        //차트 내부에 설명 텍스트
        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);

        //차트 업데이트
        barChart.invalidate();
    }
}

class MyXAxisValueFormatter extends ValueFormatter {
    private final String[] labels;

    public MyXAxisValueFormatter(String[] labels) {
        this.labels = labels;
    }

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        if (index >= 0 && index < labels.length) {
            return labels[index];
        }
        return "";
    }
}