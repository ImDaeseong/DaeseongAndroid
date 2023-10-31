package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

public class Chart6Activity extends AppCompatActivity {

    private static final String TAG = Chart6Activity.class.getSimpleName();

    private LineChart lineChart;

    private ArrayList<Entry> arrayList1;
    private ArrayList<Entry> arrayList2;
    private ArrayList<Entry> arrayList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart6);

        lineChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();

        arrayList1.add(new Entry(1, 10));
        arrayList1.add(new Entry(2, 20));
        arrayList1.add(new Entry(3, 30));
        arrayList1.add(new Entry(4, 24));
        arrayList1.add(new Entry(5, 35));

        arrayList2.add(new Entry(1, 21));
        arrayList2.add(new Entry(2, 22));
        arrayList2.add(new Entry(3, 23));
        arrayList2.add(new Entry(4, 24));
        arrayList2.add(new Entry(5, 25));

        arrayList3.add(new Entry(1, 31));
        arrayList3.add(new Entry(2, 32));
        arrayList3.add(new Entry(3, 33));
        arrayList3.add(new Entry(4, 34));
        arrayList3.add(new Entry(5, 35));

        //데이터셋 생성
        LineDataSet dataSet1 = new LineDataSet(arrayList1, "arrayList1");
        dataSet1.setColor(Color.RED);

        LineDataSet dataSet2 = new LineDataSet(arrayList2, "arrayList2");
        dataSet2.setColor(Color.BLUE);

        LineDataSet dataSet3 = new LineDataSet(arrayList3, "arrayList3");
        dataSet3.setColor(Color.GREEN);

        LineData scatterData = new LineData(dataSet1, dataSet2, dataSet3);
        lineChart.setData(scatterData);

        //그래프 설명
        Description description = new Description();
        description.setText("lineChart 그래프 설명");
        lineChart.setDescription(description);

        //차트 업데이트
        lineChart.invalidate();
    }
}