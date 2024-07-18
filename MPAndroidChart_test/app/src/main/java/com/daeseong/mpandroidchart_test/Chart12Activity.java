package com.daeseong.mpandroidchart_test;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;

public class Chart12Activity extends AppCompatActivity {

    private CombinedChart combinedChart;

    private final int nCount = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart12);

        combinedChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        // 차트 설명 비활성화
        combinedChart.getDescription().setEnabled(false);

        // 차트 배경색 설정
        combinedChart.setBackgroundColor(Color.WHITE);

        // 그리드 배경 비활성화
        combinedChart.setDrawGridBackground(false);

        // 바 그림자 비활성화
        combinedChart.setDrawBarShadow(false);

        // 전체 바 강조 비활성화
        combinedChart.setHighlightFullBarEnabled(false);

        // 차트의 그리기 순서 설정
        combinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.CANDLE
        });

        // 범례 설정
        Legend legend = combinedChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        // 오른쪽 Y축 설정
        YAxis rightAxis = combinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextColor(Color.BLACK);

        // 왼쪽 Y축 설정
        YAxis leftAxis = combinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.BLACK);

        // X축 설정
        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabelData()));
        ArrayList<String> labels = getXAxisLabelData();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        // X축의 범위 설정
        xAxis.setAxisMinimum(0f); // 최소값 설정
        xAxis.setAxisMaximum(labels.size() - 1); // 최대값 설정

        // 데이터 생성 및 설정
        CombinedData data = new CombinedData();
        data.setData(generateCandleData());
        data.setData(generateBarData());
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        // 차트에 데이터 설정 및 갱신
        combinedChart.setData(data);
        combinedChart.invalidate();
    }

    // x 축 라벨 표시
    private ArrayList<String> getXAxisLabelData() {

        ArrayList<String> xAxis = new ArrayList<>();
        for (int i = 0; i < nCount; i++) {
            xAxis.add("라벨 " + String.valueOf(i + 1));
        }
        return xAxis;
    }

    //캔들 차트
    private CandleData generateCandleData() {

        ArrayList<CandleEntry> arrayList1 = new ArrayList<>();
        arrayList1.add(new CandleEntry(0, 2200, 3900, 2000, 3500));
        arrayList1.add(new CandleEntry(1, 3500, 4600, 2900, 4200));
        arrayList1.add(new CandleEntry(2, 3900, 5400, 3800, 5100));
        arrayList1.add(new CandleEntry(3, 4800, 6500, 4600, 6100));
        arrayList1.add(new CandleEntry(4, 6000, 7200, 5700, 7000));

        CandleDataSet set = new CandleDataSet(arrayList1, "캔들차트");

        set.setDrawIcons(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        set.setColor(Color.rgb(0, 255, 0));// 캔들 색상
        set.setShadowColor(Color.DKGRAY);// 그림자 색상
        set.setShadowWidth(0.7f);// 그림자 두께

        // 캔들이 하락할 때의 색상 및 스타일 설정
        set.setDecreasingColor(Color.RED);
        set.setDecreasingPaintStyle(Paint.Style.FILL);

        // 캔들이 상승할 때의 색상 및 스타일 설정
        set.setIncreasingColor(Color.GREEN);
        set.setIncreasingPaintStyle(Paint.Style.FILL);

        // 중립 색상 설정
        set.setNeutralColor(Color.BLUE);

        // 값 텍스트 비활성화
        set.setDrawValues(false);

        return new CandleData(set);
    }

    //막대 차트
    private BarData generateBarData() {

        ArrayList<BarEntry> arrayList2 = new ArrayList<>();
        arrayList2.add(new BarEntry(1f, 41f));
        arrayList2.add(new BarEntry(2f, 82f));
        arrayList2.add(new BarEntry(3f, 63f));
        arrayList2.add(new BarEntry(4f, 44f));
        arrayList2.add(new BarEntry(5f, 54f));

        BarDataSet set = new BarDataSet(arrayList2, "막대차트");
        set.setColor(Color.BLUE);//막대 색상
        set.setValueTextColor(Color.rgb(255, 0, 0));// 막대의 값 텍스트 색상
        set.setValueTextSize(10f);//텍스트 크기
        set.setAxisDependency(YAxis.AxisDependency.RIGHT); //오른쪽 Y축 사용

        return new BarData(set);
    }
}
