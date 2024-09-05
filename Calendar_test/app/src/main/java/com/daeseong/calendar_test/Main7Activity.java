package com.daeseong.calendar_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main7Activity extends AppCompatActivity {

    private static final String TAG = Main7Activity.class.getSimpleName();

    private CalendarViewEx1 calendarviewex1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        calendarviewex1 = (CalendarViewEx1)findViewById(R.id.calendarviewex1);
    }
}
