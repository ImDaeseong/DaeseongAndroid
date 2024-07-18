package com.daeseong.mpandroidchart_test;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;

public class Chart11Activity extends AppCompatActivity {

    private BarChart barChart;
    private ArrayList<BarEntry> arrayList1;
    private ArrayList<BarEntry> arrayList2;

    float groupSpace = 0.06f;
    float barSpace = 0.02f;
    float barWidth = 0.45f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart11);

        barChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList1.add(new BarEntry(i, (float) (Math.random() * 100)));
            arrayList2.add(new BarEntry(i, (float) (Math.random() * 100)));
        }

        BarDataSet barDataSet1 = new BarDataSet(arrayList1, "arrayList1");
        barDataSet1.setColors(Color.BLUE); //막대 개별 색상 설정

        BarDataSet barDataSet2 = new BarDataSet(arrayList2, "arrayList2");
        barDataSet2.setColors(Color.GREEN); //막대 개별 색상 설정


        BarData barData = new BarData(barDataSet1, barDataSet2);

        //막대 넓이
        barData.setBarWidth(barWidth);

        barChart.setData(barData);

        //x축 시작 위치 :0,  각 그릅간의 간격, 막대간의 간격
        barChart.groupBars(0, groupSpace, groupSpace);


        //범례 설정
        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

        // 터치 리스너
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getApplicationContext(), "값: " + e.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {}
        });


        // 차트 업데이트
        barChart.getDescription().setEnabled(false); // 설명 텍스트 숨김
        barChart.invalidate();
    }

}