package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import java.util.ArrayList;

public class Chart3Activity extends AppCompatActivity {

    private static final String TAG = Chart3Activity.class.getSimpleName();

    private CandleStickChart candleStickChart;
    private ArrayList<CandleEntry> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart3);

        candleStickChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();

        arrayList1.add(new CandleEntry(0, 82200, 83900, 82000, 83500));
        arrayList1.add(new CandleEntry(1, 83500, 84600, 82900, 84200));
        arrayList1.add(new CandleEntry(2, 83900, 85400, 83800, 85100));
        arrayList1.add(new CandleEntry(3, 84800, 86500, 84600, 86100));
        arrayList1.add(new CandleEntry(4, 86000, 87200, 85700, 87000));
        arrayList1.add(new CandleEntry(5, 87500, 88800, 87000, 88200));
        arrayList1.add(new CandleEntry(6, 88000, 89500, 87800, 89200));
        arrayList1.add(new CandleEntry(7, 89000, 90400, 88600, 89800));
        arrayList1.add(new CandleEntry(8, 89700, 91200, 89500, 91000));
        arrayList1.add(new CandleEntry(9, 91000, 92500, 90800, 92200));

        CandleDataSet dataSet = new CandleDataSet(arrayList1, "arrayList1");
        dataSet.setDrawIcons(false);
        dataSet.setShadowColor(Color.DKGRAY);
        dataSet.setDecreasingColor(Color.RED); // 하락 캔들 색상
        dataSet.setIncreasingColor(Color.GREEN); // 상승 캔들 색상
        dataSet.setNeutralColor(Color.BLUE); // 중립 캔들 색상
        dataSet.setDecreasingPaintStyle(Paint.Style.FILL); // 하락 캔들 스타일
        dataSet.setIncreasingPaintStyle(Paint.Style.FILL); // 상승 캔들 스타일

        CandleData candleData = new CandleData(dataSet);
        candleStickChart.setData(candleData);

        // 차트 설정
        candleStickChart.getDescription().setEnabled(false);
        candleStickChart.getXAxis().setDrawGridLines(false);
        candleStickChart.getAxisLeft().setDrawGridLines(false);
        candleStickChart.getAxisRight().setDrawGridLines(false);
        candleStickChart.getAxisRight().setEnabled(false);
        candleStickChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        candleStickChart.getXAxis().setTextSize(10f);

        // 차트 업데이트
        candleStickChart.getDescription().setText("주식차트");
        candleStickChart.invalidate();
    }
}