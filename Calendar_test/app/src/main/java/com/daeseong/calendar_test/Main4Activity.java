package com.daeseong.calendar_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private MaterialCalendarView materialCalendarView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        materialCalendarView1 = (MaterialCalendarView)findViewById(R.id.materialCalendarView1);
        materialCalendarView1.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                Calendar calendar = materialCalendarView1.getSelectedDate().getCalendar();
                int nIndex = calendar.get(Calendar.DAY_OF_WEEK);
                switch (nIndex) {
                    case 1:
                        Log.e(TAG,"일요일");
                        break;
                    case 2:
                        Log.e(TAG, "월요일");
                        break;
                    case 3:
                        Log.e(TAG, "화요일");
                        break;
                    case 4:
                        Log.e(TAG, "수요일");
                        break;
                    case 5:
                        Log.e(TAG, "목요일");
                        break;
                    case 6:
                        Log.e(TAG, "금요일");
                        break;
                    case 7:
                        Log.e(TAG,"토요일");
                        break;
                }
            }
        });

        //시작시 설정
        materialCalendarView1.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2020 , 0, 1))
                .setMaximumDate(CalendarDay.from(2020, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS )
                .commit();

    }
}
