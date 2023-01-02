package com.daeseong.paging_test.Common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime {

    //오늘
    public static String getToday(){

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sResult = sDateFormat.format(new java.util.Date());
        return sResult;
    }

    //하루전
    public static String getOneDayago(){

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String sResult = sDateFormat.format(calendar.getTime());

        return sResult;
    }
}
