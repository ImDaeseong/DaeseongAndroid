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

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private Button button1, button2;
    private boolean bclicked1 = false, bclicked2 = false;

    private AlarmManager alarmManager = null;
    private PendingIntent alarmpendingIntent = null;
    private int AlarmID = 0;
    private Intent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bclicked1){
                    cancelAlaram1();
                    bclicked1 = false;
                }else {
                    setAlaram1();
                    bclicked1 = true;
                }

            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bclicked2){
                    cancelAlaram2();
                    bclicked2 = false;
                }else {
                    setAlaram2();
                    bclicked2 = true;
                }

            }
        });

    }

    private void  setAlaram1(){

        AlarmID = 1;
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmpendingIntent = PendingIntent.getBroadcast(Main4Activity.this, AlarmID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        if(alarmpendingIntent == null){
            Log.e(TAG, "설정된 알람이 없음");
        }else {
            Log.e(TAG, "설정된 알람이 있음");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmpendingIntent);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String sTime = simpleDateFormat.format(calendar.getTime());
        Log.e(TAG, "1번 설정된 알람 시간 : " + sTime);
    }

    private void  cancelAlaram1(){

        AlarmID = 1;
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmpendingIntent = PendingIntent.getBroadcast(Main4Activity.this, AlarmID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(alarmpendingIntent != null){

            Log.e(TAG, "1번 알람 취소");

            alarmManager.cancel(alarmpendingIntent);
            alarmpendingIntent.cancel();
        }

    }



    private void  setAlaram2(){

        AlarmID = 2;
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmpendingIntent = PendingIntent.getBroadcast(Main4Activity.this, AlarmID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        if(alarmpendingIntent == null){
            Log.e(TAG, "설정된 알람이 없음");
        }else {
            Log.e(TAG, "설정된 알람이 있음");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmpendingIntent);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String sTime = simpleDateFormat.format(calendar.getTime());
        Log.e(TAG, "2번 설정된 알람 시간 : " + sTime);
    }

    private void  cancelAlaram2(){

        AlarmID = 2;
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmpendingIntent = PendingIntent.getBroadcast(Main4Activity.this, AlarmID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(alarmpendingIntent != null){

            Log.e(TAG, "2번 알람 취소");

            alarmManager.cancel(alarmpendingIntent);
            alarmpendingIntent.cancel();
        }

    }

}
