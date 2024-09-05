package com.daeseong.calendar_test;

import android.graphics.Color;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
import java.util.Collection;
import java.util.HashSet;

public class DayViewDecoratorEx3 implements DayViewDecorator {

    private static final String TAG = DayViewDecoratorEx3.class.getSimpleName();

    private HashSet<CalendarDay> calendarDays;

    public DayViewDecoratorEx3(Collection<CalendarDay> calendarDays){
        this.calendarDays = new HashSet<>(calendarDays);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return calendarDays.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, Color.rgb(255, 0, 0)));
    }
}
