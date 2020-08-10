package com.daeseong.calendar_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main6Activity.class.getSimpleName();

    private CalendarViewEx calendarviewex1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main6);

        calendarviewex1 = (CalendarViewEx)findViewById(R.id.calendarviewex1);

        //Calendar calender = Calendar.getInstance(Locale.getDefault());
        //int nMaxDay = calender.getActualMaximum(calender.DAY_OF_MONTH);
        //Log.e(TAG, "nMaxDay:" + nMaxDay);

    }
}
