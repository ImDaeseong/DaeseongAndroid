package com.daeseong.calendar_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private MaterialCalendarView materialCalendarView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        materialCalendarView1 = (MaterialCalendarView)findViewById(R.id.materialCalendarView1);
        materialCalendarView1.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {



            }
        });

        //시작시 설정
        materialCalendarView1.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2020 , 0, 1))
                .setMaximumDate(CalendarDay.from(2020, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS )
                .commit();


        //데코 초기화
        materialCalendarView1.removeDecorators();


        //토요일,일요일 색상 변경
        materialCalendarView1.addDecorators(
                new DayViewDecoratorEx(),
                new DayViewDecoratorEx1());



        materialCalendarView1.clearSelection();


        //2020.8.5 밑줄, 사이즈, 색상 변경
        DayViewDecoratorEx2 day1 = new DayViewDecoratorEx2(CalendarDay.from(2020, 7, 5));
        materialCalendarView1.addDecorators(day1);


        //오늘 날짜 밑줄, 사이즈, 색상 변경
        CalendarDay today = CalendarDay.today();
        DayViewDecoratorEx2 day2 = new DayViewDecoratorEx2(CalendarDay.from(today.getYear(), today.getMonth(), today.getDay()));
        materialCalendarView1.addDecorators(day2);


        //2020.8.10, 2020.8.11 밑줄, 사이즈, 색상 변경 ArrayList
        ArrayList<CalendarDay> arrayList = new ArrayList<>();
        CalendarDay calendar1 = CalendarDay.from(2020, 7, 10);
        CalendarDay calendar2 = CalendarDay.from(2020, 7, 11);
        arrayList.add(calendar1);
        arrayList.add(calendar2);
        materialCalendarView1.addDecorators(new DayViewDecoratorEx3(arrayList));


        //2020.8.12 밑줄, 사이즈, 색상 변경 HashSet(중복 데이타 자동 처리됨)
        HashSet<CalendarDay> hashSet = new HashSet<>();
        hashSet.add(CalendarDay.from(2020, 7, 11));
        hashSet.add(CalendarDay.from(2020, 7, 12));
        materialCalendarView1.addDecorators(new DayViewDecoratorEx3(hashSet));

    }
}
