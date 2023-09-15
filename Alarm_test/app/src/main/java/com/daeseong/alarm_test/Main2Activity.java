package com.daeseong.alarm_test;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private AlarmManager alarmManager = null;
    private PendingIntent alarmpendingIntent = null;
    private Intent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
        alarmpendingIntent = PendingIntent.getBroadcast(Main2Activity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmpendingIntent);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String sTime = simpleDateFormat.format(calendar.getTime());
        Log.e(TAG, "설정된 알람 시간 : " + sTime);
    }

    private void DestroyAlarm(){

        if(alarmpendingIntent != null) {
            alarmpendingIntent = PendingIntent.getBroadcast(Main2Activity.this,0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            alarmManager.cancel(alarmpendingIntent);
            alarmpendingIntent.cancel();
            alarmManager = null;
            alarmpendingIntent = null;
        }
    }

}
