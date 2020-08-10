package com.daeseong.calendar_test;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class DayViewDecoratorEx implements DayViewDecorator {

    private static final String TAG = DayViewDecoratorEx.class.getSimpleName();

    private final Calendar calendar;

    public DayViewDecoratorEx(){
        calendar = Calendar.getInstance();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {

        day.copyTo(calendar);
        int nIndex = calendar.get(Calendar.DAY_OF_WEEK);
        /*
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
        */
        return nIndex == Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)));
        //view.addSpan(new ForegroundColorSpan(Color.RED));
        //view.addSpan(new DotSpan(5, Color.rgb(255, 0, 0)));
    }
}
