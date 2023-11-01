package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

public class Chart4Activity extends AppCompatActivity {

    private static final String TAG = Chart4Activity.class.getSimpleName();

    private CombinedChart combinedChart;

    private ArrayList<Entry> arrayList1;
    private ArrayList<BarEntry> arrayList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart4);

        combinedChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();

        arrayList1.add(new Entry(1, 10));
        arrayList1.add(new Entry(2, 20));
        arrayList1.add(new Entry(3, 30));
        arrayList1.add(new Entry(4, 24));
        arrayList1.add(new Entry(5, 35));

        //라인 차트
        LineDataSet lineDataSet = new LineDataSet(arrayList1, "arrayList1");
        lineDataSet.setColor(Color.RED);

        arrayList2 = new ArrayList<>();

        arrayList2.add(new BarEntry(1f, 41f));
        arrayList2.add(new BarEntry(2f, 82f));
        arrayList2.add(new BarEntry(3f, 63f));
        arrayList2.add(new BarEntry(4f, 44f));
        arrayList2.add(new BarEntry(5f, 54f));

        //바 차트
        BarDataSet barDataSet = new BarDataSet(arrayList2, "arrayList1");
        barDataSet.setColor(Color.GRAY);

        //차트에 데이터 설정
        CombinedData combinedData = new CombinedData();
        combinedData.setData(new LineData(lineDataSet)); // 라인 차트 데이터
        combinedData.setData(new BarData(barDataSet)); // 바 차트 데이터
        combinedChart.setData(combinedData);

        //차트 업데이트
        combinedChart.getDescription().setEnabled(false);// 설명 비활성화
        combinedChart.invalidate();
    }
}