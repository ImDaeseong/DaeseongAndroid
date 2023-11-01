package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;

public class Chart1Activity extends AppCompatActivity {

    private static final String TAG = Chart1Activity.class.getSimpleName();

    private BarChart barChart;
    private ArrayList<BarEntry> arrayList1;
    private List<Integer> barColors;
    private List<String> xlabels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart1);

        barChart = findViewById(R.id.chart);
        barChart.getAxisRight().setDrawLabels(false);// 오른쪽 Y축 숨김

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();

        arrayList1.add(new BarEntry(0f, 41f));
        arrayList1.add(new BarEntry(1f, 82f));
        arrayList1.add(new BarEntry(2f, 63f));
        arrayList1.add(new BarEntry(3f, 44f));

        //막대 색상
        barColors = new ArrayList<>();
        barColors.add(Color.BLUE);
        barColors.add(Color.RED);
        barColors.add(Color.GREEN);
        barColors.add(Color.YELLOW);

        BarDataSet barDataSet = new BarDataSet(arrayList1, "arrayList1");
        barDataSet.setColors(barColors); //막대 개별 색상 설정
        //barDataSet.setColor(Color.BLUE); // 막대의 색상 설정 전부 동일하게
        barDataSet.setValueTextColor(Color.BLACK); // 막대에 표시되는 값의 텍스트 색상 설정

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        // 왼쪽 Y축 설정
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        // X축 설정
        for (int i = 0; i < arrayList1.size(); i++) {
            xlabels.add("label" + (i + 1));
        }

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xlabels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        // 차트 업데이트
        barChart.getDescription().setEnabled(false); // 설명 텍스트 숨김
        barChart.invalidate();
    }
}
