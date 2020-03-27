package com.daeseong.alarm_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private AlarmManager alarmManager = null;
    private PendingIntent alarmpendingIntent = null;
    private Intent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        setAlaram();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DestroyAlarm();
    }

    private void  setAlaram(){

        DestroyAlarm();

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmpendingIntent = PendingIntent.getBroadcast(Main3Activity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmpendingIntent);

        //1분마다 반복
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 1, alarmpendingIntent);

        //20분마다 반복
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 20, alarmpendingIntent);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String sTime = simpleDateFormat.format(calendar.getTime());
        Log.e(TAG, "설정된 알람 시간 : " + sTime);
    }

    private void DestroyAlarm(){

        if(alarmpendingIntent != null) {
            alarmpendingIntent = PendingIntent.getBroadcast(Main3Activity.this,0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(alarmpendingIntent);
            alarmpendingIntent.cancel();
            alarmManager = null;
            alarmpendingIntent = null;
        }
    }

}
