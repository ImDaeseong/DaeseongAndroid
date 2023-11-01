package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

public class Chart5Activity extends AppCompatActivity {

    private static final String TAG = Chart5Activity.class.getSimpleName();

    private HorizontalBarChart horizontalBarChart;
    private ArrayList<BarEntry> arrayList1;
    private List<String> xlabels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart5);

        horizontalBarChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();

        arrayList1.add(new BarEntry(0f, 24f));
        arrayList1.add(new BarEntry(1f, 20f));
        arrayList1.add(new BarEntry(2f, 18f));
        arrayList1.add(new BarEntry(3f, 11f));
        arrayList1.add(new BarEntry(4f, 10f));
        arrayList1.add(new BarEntry(5f, 8f));

        BarDataSet barDataSet = new BarDataSet(arrayList1, "arrayList1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(14f);

        BarData barData = new BarData(barDataSet);
        horizontalBarChart.setData(barData);
        horizontalBarChart.animateY(2000);
        horizontalBarChart.setFitBars(true);

        //범례 설정
        Legend legend = horizontalBarChart.getLegend();
        legend.setEnabled(true);

        //그래프 설명
        Description description = new Description();
        description.setText("HorizontalBarChart 설명");
        horizontalBarChart.setDescription(description);

        horizontalBarChart.getXAxis().setLabelCount(6);
        horizontalBarChart.setExtraRightOffset(5f);

        // X축 설정
        for (int i = 0; i < arrayList1.size(); i++) {
            xlabels.add("label" + (i + 1));
        }

        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1);
        xAxis.setDrawLabels(true);
        xAxis.setXOffset(10);
        xAxis.setDrawAxisLine(true);

        //X축 레이블 설정
        horizontalBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xlabels));
        horizontalBarChart.getXAxis().setGranularity(0.2f);
        horizontalBarChart.getXAxis().setGranularityEnabled(true);
        horizontalBarChart.setVisibleXRangeMaximum(6);

        // 차트 업데이트
        horizontalBarChart.invalidate();
    }
}