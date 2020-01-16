package com.daeseong.fcm_test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseMessagingServiceEx extends FirebaseMessagingService {

    private static final String TAG = FirebaseMessagingServiceEx.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "getFrom : " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {

            Log.d(TAG, "getData : " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {

            Log.d(TAG, "getTitle : " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "getBody : " + remoteMessage.getNotification().getBody());

            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody() );
        }
    }

    private void sendNotification(String sTitle, String sMessage) {

        int notifyID = 1;
        String schannelID = "daeseong_01";
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, schannelID); //For > API26 (OREO)
        } else {
            builder = new Notification.Builder(this);
        }

        builder.setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(sTitle)
                .setContentText(sMessage)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);

        NotificationManager notify = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(schannelID,"알림", NotificationManager.IMPORTANCE_DEFAULT);
            notify.createNotificationChannel(channel);
        }
        notify.notify(notifyID, builder.build());
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
