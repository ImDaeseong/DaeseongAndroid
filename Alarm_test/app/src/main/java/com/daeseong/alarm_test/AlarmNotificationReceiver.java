package com.daeseong.alarm_test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmNotificationReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmNotificationReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        //호출후 삭제시 필요
        int nID = intent.getExtras().getInt("alarmID");

        Log.e(TAG, "호출 시간 : " + getTimeDate() + " ID:" + nID);

        //activity에 전달
        Intent iID = new Intent("com.daeseong.alarm_test.ID");
        iID.putExtra("alarmID", nID);
        context.sendBroadcast(iID);

        int notifyID = 1;
        String schannelID = "daeseong_01";
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, schannelID); //For > API26 (OREO)
        } else {
            builder = new Notification.Builder(context);
        }

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("제목")
                .setContentText("내용")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);

        NotificationManager notify = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(schannelID,"알림", NotificationManager.IMPORTANCE_DEFAULT);
            notify.createNotificationChannel(channel);
        }
        notify.notify(notifyID, builder.build());

    }

    private static String getTimeDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

}
