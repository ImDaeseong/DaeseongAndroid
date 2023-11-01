package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import java.util.ArrayList;

public class Chart2Activity extends AppCompatActivity {

    private static final String TAG = Chart2Activity.class.getSimpleName();

    private BubbleChart bubbleChart;

    private ArrayList<BubbleEntry> arrayList1;
    private ArrayList<BubbleEntry> arrayList2;
    private ArrayList<BubbleEntry> arrayList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart2);

        bubbleChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();

        arrayList1.add(new BubbleEntry(4, 10, 5)); // X, Y, Size
        arrayList1.add(new BubbleEntry(5, 12, 20));
        arrayList1.add(new BubbleEntry(6, 14, 15));

        arrayList2.add(new BubbleEntry(4, 18, 25));
        arrayList2.add(new BubbleEntry(5, 20, 30));
        arrayList2.add(new BubbleEntry(6, 22, 35));

        arrayList3.add(new BubbleEntry(4, 26, 40));
        arrayList3.add(new BubbleEntry(5, 28, 45));
        arrayList3.add(new BubbleEntry(6, 30, 50));

        //데이터셋 생성
        BubbleDataSet dataSet1 = new BubbleDataSet (arrayList1, "arrayList1");
        dataSet1.setColor(Color.BLUE);

        BubbleDataSet dataSet2 = new BubbleDataSet (arrayList2, "arrayList2");
        dataSet2.setColor(Color.RED);

        BubbleDataSet dataSet3 = new BubbleDataSet (arrayList3, "arrayList3");
        dataSet3.setColor(Color.GREEN);

        BubbleData bubbleData = new BubbleData (dataSet1, dataSet2, dataSet3);
        bubbleChart.setData(bubbleData);

        //차트 업데이트
        bubbleChart.getDescription().setEnabled(false);// 설명 비활성화
        bubbleChart.invalidate();
    }
}