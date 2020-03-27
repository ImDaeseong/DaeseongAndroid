package com.daeseong.alarm_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private Button button1, button2;

    private AlarmManager alarmManager = null;
    private PendingIntent alarmpendingIntent = null;
    private Intent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlaram(false);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlaram(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DestroyAlarm();
    }

    private void  setAlaram(boolean bRepeat){

        if(bRepeat) {

            DestroyAlarm();

            // 반복
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmIntent = new Intent(this, AlarmReceiver.class);
            alarmpendingIntent = PendingIntent.getBroadcast(Main1Activity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 5);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5000, alarmpendingIntent);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            String sTime = simpleDateFormat.format(calendar.getTime());
            Log.e(TAG, "설정된 알람 시간 : " + sTime);

        } else {

            DestroyAlarm();

            // 1회용
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmIntent = new Intent(this, AlarmReceiver.class);
            alarmpendingIntent = PendingIntent.getBroadcast(Main1Activity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 5);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmpendingIntent);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            String sTime = simpleDateFormat.format(calendar.getTime());
            Log.e(TAG, "설정된 알람 시간 : " + sTime);
        }
    }

    private void DestroyAlarm(){

        if(alarmpendingIntent != null) {
            alarmpendingIntent = PendingIntent.getBroadcast(Main1Activity.this,0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(alarmpendingIntent);
            alarmpendingIntent.cancel();
            alarmManager = null;
            alarmpendingIntent = null;
        }
    }

}
