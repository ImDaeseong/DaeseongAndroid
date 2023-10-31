package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import java.util.ArrayList;

public class Chart8Activity extends AppCompatActivity {

    private static final String TAG = Chart8Activity.class.getSimpleName();

    //다각형 형태의 차트
    private RadarChart radarChart;

    private ArrayList<RadarEntry> arrayList1;
    private ArrayList<RadarEntry> arrayList2;
    private ArrayList<RadarEntry> arrayList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart8);

        radarChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();

        arrayList1.add(new RadarEntry(50f));
        arrayList1.add(new RadarEntry(90f));
        arrayList1.add(new RadarEntry(30f));
        arrayList1.add(new RadarEntry(60f));
        arrayList1.add(new RadarEntry(80f));

        arrayList2.add(new RadarEntry(70f));
        arrayList2.add(new RadarEntry(75f));
        arrayList2.add(new RadarEntry(89f));
        arrayList2.add(new RadarEntry(79f));
        arrayList2.add(new RadarEntry(95f));

        arrayList3.add(new RadarEntry(80f));
        arrayList3.add(new RadarEntry(77f));
        arrayList3.add(new RadarEntry(78f));
        arrayList3.add(new RadarEntry(99f));
        arrayList3.add(new RadarEntry(87f));

        //데이터셋 생성
        RadarDataSet dataSet1 = new RadarDataSet(arrayList1, "arrayList1");
        dataSet1.setColor(Color.RED);
        dataSet1.setFillColor(Color.WHITE);
        dataSet1.setDrawFilled(true);
        dataSet1.setFillAlpha(180);
        dataSet1.setLineWidth(4f);
        dataSet1.setDrawHighlightCircleEnabled(true);
        dataSet1.setDrawHighlightIndicators(false);

        RadarDataSet dataSet2 = new RadarDataSet(arrayList2, "arrayList2");
        dataSet2.setColor(Color.BLUE);
        dataSet2.setFillColor(Color.WHITE);
        dataSet2.setDrawFilled(true);
        dataSet2.setFillAlpha(180);
        dataSet2.setLineWidth(4f);
        dataSet2.setDrawHighlightCircleEnabled(true);
        dataSet2.setDrawHighlightIndicators(false);

        RadarDataSet dataSet3 = new RadarDataSet(arrayList3, "arrayList3");
        dataSet3.setColor(Color.GREEN);
        dataSet3.setFillColor(Color.WHITE);
        dataSet3.setDrawFilled(true);
        dataSet3.setFillAlpha(180);
        dataSet3.setLineWidth(4f);
        dataSet3.setDrawHighlightCircleEnabled(true);
        dataSet3.setDrawHighlightIndicators(false);

        RadarData radarData = new RadarData(dataSet1, dataSet2, dataSet3);
        radarChart.setData(radarData);

        //차트 업데이트
        radarChart.invalidate();
    }
}