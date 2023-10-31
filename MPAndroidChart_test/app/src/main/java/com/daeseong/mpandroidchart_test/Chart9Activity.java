package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import java.util.ArrayList;

public class Chart9Activity extends AppCompatActivity {

    private static final String TAG = Chart9Activity.class.getSimpleName();

    //데이터를 점(또는 마커)으로 나타내며, 이 점들은 데이터의 분포나 관계를 표시한다.
    private ScatterChart scatterChart;

    private ArrayList<Entry> arrayList1;
    private ArrayList<Entry> arrayList2;
    private ArrayList<Entry> arrayList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart9);

        scatterChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();

        arrayList1.add(new Entry(1f, 11f));
        arrayList1.add(new Entry(2f, 12f));
        arrayList1.add(new Entry(3f, 13f));
        arrayList1.add(new Entry(4f, 14f));
        arrayList1.add(new Entry(5f, 15f));

        arrayList2.add(new Entry(1f, 21f));
        arrayList2.add(new Entry(2f, 22f));
        arrayList2.add(new Entry(3f, 23f));
        arrayList2.add(new Entry(4f, 24f));
        arrayList2.add(new Entry(5f, 25f));

        arrayList3.add(new Entry(1f, 31f));
        arrayList3.add(new Entry(2f, 32f));
        arrayList3.add(new Entry(3f, 33f));
        arrayList3.add(new Entry(4f, 34f));
        arrayList3.add(new Entry(5f, 35f));

        //데이터셋 생성
        ScatterDataSet dataSet1 = new ScatterDataSet(arrayList1, "arrayList1");
        dataSet1.setColor(Color.RED);
        dataSet1.setScatterShape(ScatterChart.ScatterShape.CIRCLE);

        ScatterDataSet dataSet2 = new ScatterDataSet(arrayList2, "arrayList2");
        dataSet2.setColor(Color.BLUE);
        dataSet2.setScatterShape(ScatterChart.ScatterShape.CIRCLE);

        ScatterDataSet dataSet3 = new ScatterDataSet(arrayList3, "arrayList3");
        dataSet3.setColor(Color.GREEN);
        dataSet3.setScatterShape(ScatterChart.ScatterShape.CIRCLE);

        ScatterData scatterData = new ScatterData(dataSet1, dataSet2, dataSet3);
        scatterChart.setData(scatterData);

        //차트 업데이트
        scatterChart.invalidate();
    }
}