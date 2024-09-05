package com.daeseong.calendar_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private MaterialCalendarView materialCalendarView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        materialCalendarView1 = (MaterialCalendarView)findViewById(R.id.materialCalendarView1);

        materialCalendarView1.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                String sDay = String.format("%02d/%02d/%04d", (date.getMonth() + 1), date.getDay(), date.getYear());
                Log.e(TAG, "sDay:" + sDay);
            }
        });

        materialCalendarView1.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                Log.e(TAG, "month:" + date.getMonth() + 1);
            }
        });


        //선택된 요일 색상
        materialCalendarView1.setSelectionColor(Color.GRAY);

        //달력 시작일 설정
        materialCalendarView1.state().edit().setMinimumDate(CalendarDay.from(2020,8,1)).commit();

        //일요일 색상만 red
        materialCalendarView1.addDecorators(new DayViewDecoratorEx());

    }
}
