package com.daeseong.calendar_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import java.util.Calendar;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private CalendarView calendarview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        calendarview1 = findViewById(R.id.calendarview1);

        calendarview1.setFirstDayOfWeek(Calendar.MONDAY);//.SUNDAY);//달력의 시작일 설정(default 일요일)

        //달력 유효기간 설정
        setCalendarDay1();

        calendarview1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String sDay = String.format("%02d/%02d/%04d", (month + 1), dayOfMonth, year);
                Log.e(TAG, "sDay:" + sDay);
            }
        });

    }

    private void setCalendarDay1(){

        //지금 시간부터
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendarview1.setMinDate(cal.getTimeInMillis());

        //한달 후까지
        cal.add(Calendar.MONTH, 1);
        calendarview1.setMaxDate(cal.getTimeInMillis());
    }

    private void setCalendarDay2(){

        //이번달 5일부터
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        calendarview1.setMinDate(calendar.getTimeInMillis());

        //다음달 15일까지
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendarview1.setMaxDate(calendar.getTimeInMillis());
    }

    private void setCalendarDay3(){

        //07/01/2020
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, 6, 1);

        //09/30/2020
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020, 9, 30);

        calendarview1.setMinDate(calendar1.getTimeInMillis());
        calendarview1.setMaxDate(calendar2.getTimeInMillis());
    }

}
