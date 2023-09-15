package com.daeseong.mpandroidchart_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private Button button1;

    private LineChart lineChart;
    private XAxis xAxis;
    private YAxis yAxis;

    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private LineData lineData;
    private LineDataSet lineDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

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

        lineChart = (LineChart)findViewById(R.id.linechart);
        lineChart.setDoubleTapToZoomEnabled(false);	//더블탭으로 확대 여부
        lineChart.setClickable(false);				//클릭가능 여부
        lineChart.setDragEnabled(true);              //드래그 여부
        lineChart.setScaleEnabled(false);            //크기 조절 여부
        lineChart.setPinchZoom(false);               //핀치 가능 여부

        //내부 텍스트 숨기기
        lineChart.setDescription(null);
        /*
        //내부 텍스트 보이기
        Description description = new Description();
        description.setText("내부 텍스트");
        lineChart.setDescription(description);
        */

        //x축
        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLUE);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);


        //y축
        yAxis = lineChart.getAxisLeft();//yAxis = lineChart.getAxisRight();
        yAxis.setDrawLabels(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);
        yAxis.setDrawZeroLine(true);
        lineChart.getAxisRight().setEnabled(false);//lineChart.getAxisLeft().setEnabled(false);


        //legend 영역 미설정시 default

        /*
        //legend 영역 숨기기
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        */

        /*
        //legend 영역 설정
        Legend legend = lineChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setTextSize(10f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.CIRCLE);
        */
   }

    private void initData(){

        entries.clear();

        lineChart.invalidate();
        lineChart.clear();

        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 3));
        entries.add(new Entry(4, 4));
        entries.add(new Entry(5, 5));

        lineDataSet = new LineDataSet(entries, "입력 데이터");
        lineDataSet.setColor(Color.RED);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.RED);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setCubicIntensity(0.1f);

        lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
    }

}
