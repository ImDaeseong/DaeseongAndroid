package com.daeseong.calendar_test;


import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;


public class DayViewDecoratorEx2 implements DayViewDecorator {

    private static final String TAG = DayViewDecoratorEx2.class.getSimpleName();

    private CalendarDay calendarDay;

    public DayViewDecoratorEx2(){
    }

    public DayViewDecoratorEx2(CalendarDay calendarDay){
        this.calendarDay = calendarDay;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {

        //String sDay = String.format("%02d/%02d/%04d", day.getMonth(), day.getDay(), day.getYear());
        //Log.e(TAG, "sDay:" + sDay);

        return calendarDay != null && day.equals(calendarDay);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new AbsoluteSizeSpan(50));
        view.addSpan(new ForegroundColorSpan(Color.BLUE));
        view.addSpan(new UnderlineSpan());
    }

    public void setCalendarDay(CalendarDay calendarDay){
        this.calendarDay = calendarDay;
    }
}


