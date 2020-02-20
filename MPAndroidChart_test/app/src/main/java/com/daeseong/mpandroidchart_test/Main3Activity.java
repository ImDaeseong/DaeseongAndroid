package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private Button button1;

    private BarChart barChart;
    private XAxis xAxis;
    private YAxis yAxis;

    private ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
    private BarData barData;
    private BarDataSet barDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

        //차트 초기화
        initChart();

        //데이타 입력
        initData();
    }

    private void initChart() {

        barChart = (BarChart)findViewById(R.id.barchart);
        barChart.setDoubleTapToZoomEnabled(false);	//더블탭으로 확대 여부
        barChart.setClickable(false);				//클릭가능 여부
        barChart.setDragEnabled(true);              //드래그 여부
        barChart.setScaleEnabled(false);            //크기 조절 여부
        barChart.setPinchZoom(false);               //핀치 가능 여부

        //x축
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLUE);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);


        //y축
        yAxis = barChart.getAxisLeft();
        yAxis.setDrawLabels(true);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawGridLines(true);
        yAxis.setDrawZeroLine(true);
        barChart.getAxisRight().setEnabled(false);


        //내부 텍스트 숨기기
        barChart.setDescription(null);

        //legend 영역 숨기기
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
    }

    private void initData(){

        entries.clear();

        barChart.invalidate();
        barChart.clear();

        entries.add(new BarEntry(1, 1));
        entries.add(new BarEntry(2, 2));
        entries.add(new BarEntry(3, 3));
        entries.add(new BarEntry(4, 4));
        entries.add(new BarEntry(5, 5));

        barDataSet = new BarDataSet(entries, "입력 데이터");
        barDataSet.setColor(Color.RED);
        barDataSet.setValueTextSize(14f);

        barData = new BarData(barDataSet);

        barChart.setData(barData);
    }
}
