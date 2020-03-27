package com.daeseong.alarm_test;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Alarm_util {

    private static final String TAG = Alarm_util.class.getName();

    private AlarmManager alarmManager = null;
    private PendingIntent alarmpendingIntent = null;
    private Context mcontext;
    private Calendar calendar = null;

    private static Alarm_util instance;
    public static Alarm_util getInstance(){
        if( instance == null){
            instance = new Alarm_util();
        }
        return instance;
    }

    public void init(Context context){

        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mcontext = context;
    }

    public void  AddAlaram(int nID, int nHour, int nMinute){

        //Intent alarmIntent = new Intent(mcontext, AlarmReceiver.class);
        Intent alarmIntent = new Intent(mcontext, AlarmNotificationReceiver.class);

        //호출후 삭제 위해 꼭 넘겨야 한다.
        alarmIntent.putExtra("alarmID", nID);

        alarmpendingIntent = PendingIntent.getBroadcast(mcontext, nID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, nHour);
        calendar.set(Calendar.MINUTE, nMinute);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmpendingIntent);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String sTime = simpleDateFormat.format(calendar.getTime());
        //Log.e(TAG, "설정된 알람 시간 : " + sTime);
    }

    public void DeleteAlarm(int nID){

        //Intent alarmIntent = new Intent(mcontext, AlarmReceiver.class);
        Intent alarmIntent = new Intent(mcontext, AlarmNotificationReceiver.class);
        alarmpendingIntent = PendingIntent.getBroadcast(mcontext, nID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(alarmpendingIntent);
        alarmpendingIntent.cancel();
        //Log.e(TAG, "삭제된 알람 ID : " + nID);
    }

}
