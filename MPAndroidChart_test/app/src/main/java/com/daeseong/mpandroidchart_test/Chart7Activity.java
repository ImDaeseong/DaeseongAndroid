package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class Chart7Activity extends AppCompatActivity {

    private static final String TAG = Chart7Activity.class.getSimpleName();

    private PieChart pieChart;

    private ArrayList<PieEntry> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart7);

        pieChart = findViewById(R.id.chart);

        init();
    }

    private void init() {

        arrayList1 = new ArrayList<>();

        arrayList1.add(new PieEntry(60f, "데이터1"));
        arrayList1.add(new PieEntry(90f, "데이터2"));
        arrayList1.add(new PieEntry(75f, "데이터3"));
        arrayList1.add(new PieEntry(55f, "데이터4"));

        PieDataSet dataSet = new PieDataSet(arrayList1, "제목");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // 색상 설정

        PieData pieData = new PieData(dataSet);

        // PieChart 설정
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false); // 설명 비활성화
        pieChart.setCenterText("데이터 항목"); // 가운데 텍스트 설정
        pieChart.setHoleRadius(20f); // 중심 구멍 크기
        pieChart.setTransparentCircleRadius(25f); // 투명한 원 크기
        pieChart.animateY(1000); // 애니메이션 적용
    }
}