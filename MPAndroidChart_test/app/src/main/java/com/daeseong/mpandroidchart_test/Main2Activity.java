package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private Button button1;

    private PieChart pieChart;

    private ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
    private PieData pieData;
    private PieDataSet pieDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

        pieChart = (PieChart)findViewById(R.id.piechart);
        pieChart.setClickable(false);	//클릭가능 여부

        //내부 텍스트 숨기기
        pieChart.setDescription(null);

        //legend 영역 숨기기
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
    }

    private void initData(){

        entries.clear();

        pieChart.invalidate();
        pieChart.clear();

        entries.add(new PieEntry(50, 1));
        entries.add(new PieEntry(20, 2));
        entries.add(new PieEntry(30, 3));

        pieDataSet = new PieDataSet(entries, "입력 데이터");
        pieDataSet.setColor(Color.RED);

        pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);

        //pieChart.animateXY(5000, 5000);
    }
}
