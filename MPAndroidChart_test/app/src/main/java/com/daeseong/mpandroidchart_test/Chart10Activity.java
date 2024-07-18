package com.daeseong.mpandroidchart_test;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;

public class Chart10Activity extends AppCompatActivity {

    private LineChart lineChart;

    private ArrayList<Entry> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart10);

        lineChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            arrayList1.add(new Entry(i, (float) (Math.random() * 10)));
        }

        //데이터셋 생성
        LineDataSet dataSet1 = new LineDataSet(arrayList1, "arrayList1");
        dataSet1.setColor(Color.BLACK);
        dataSet1.setCircleColor(Color.RED);
        dataSet1.setDrawFilled(true);
        dataSet1.setFillColor(Color.GREEN);

        //부드러운 커브를 사용한다면
        //dataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData scatterData = new LineData(dataSet1);
        lineChart.setData(scatterData);

        // 애니메이션
        lineChart.animateX(1500);

        // 마커 설정
        //MarkerView mv = new MarkerView(this, R.layout.layout_markview);
        MarkerViewEx mv = new MarkerViewEx(this, R.layout.layout_markview);
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);

        // X축 설정
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"월", "화", "수", "목", "금", "토", "일"}));

        // Y축 설정
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(12f);

        // 차트 설명 숨김
        lineChart.getDescription().setEnabled(false);

        // 차트 갱신
        lineChart.invalidate();
    }
}