package com.daeseong.fcm_test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
//import androidx.annotation.NonNull;
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//import java.util.Map;
//import java.util.Random;
//import android.os.Build;

public class FirebaseMessagingServiceEx extends FirebaseMessagingService {

    private static final String TAG = FirebaseMessagingServiceEx.class.getSimpleName();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        try {

            if (!message.getData().isEmpty()) {
                sendNotification(message);
            }

        } catch (Exception ex) {
            Log.e(TAG, "onMessageReceived: ", ex);
        }
    }

    @Override
    public void handleIntent(Intent intent) {

        try {

            if (intent.getExtras() == null || intent.getExtras().isEmpty()) {
                super.handleIntent(intent);
                return;
            }

            RemoteMessage.Builder builder = new RemoteMessage.Builder("FirebaseMessageReceiver");
            for (String key : intent.getExtras().keySet()) {
                Object value = intent.getExtras().get(key);
                if (value != null) {
                    builder.addData(key, value.toString());
                }
            }

            if (!builder.getData().isEmpty()) {
                sendNotification(builder.build());
            }

        } catch (Exception ex) {
            Log.e(TAG, "handleIntent: ", ex);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e(TAG, "onNewToken: " + token);
    }

    private void sendNotification(RemoteMessage remoteMessage) {

        try {

            Map<String, String> data = remoteMessage.getData();
            Log.e(TAG, "data: " + data.toString());

            //처음 앱 설치시 이쪽으로 들어오므로 토큰값이 있으면 리턴
            String sToken = data.get("token");
            if (sToken != null) {
                Log.e(TAG, "sendNotification token: " + sToken);
                return;
            }


            // Intent 설정
            Intent intent = new Intent(this, PushActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // PendingIntent 설정
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


            // 알림 기본 설정
            String sChannelId = getString(R.string.channel_id);
            Uri uriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            // 알림 내용 설정
            String sTitle = "제목";
            String sMessage = "내용";

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, sChannelId)
                    .setSmallIcon(R.drawable.ic_push_icons)
                    .setContentTitle(sTitle)
                    .setContentText(sMessage)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setSound(uriSound).setLights(173, 500, 2000)
                    .setContentIntent(pendingIntent);
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(sMessage));

            // 알림 표시
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.notify(11, builder.build());
            }

        } catch (Exception ex) {
            Log.e(TAG, "sendNotification: ", ex);
        }
    }
}


//이전 버전
/*
public class FirebaseMessagingServiceEx extends FirebaseMessagingService {

    private static final String TAG = FirebaseMessagingServiceEx.class.getSimpleName();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.e(TAG, "onMessageReceived:" + message);

        try {

            String sTitle = message.getNotification() != null ? message.getNotification().getTitle() : "";
            String sMessage = message.getNotification() != null ? message.getNotification().getBody() : "";

            Log.e(TAG, "sTitle:" + sTitle);
            Log.e(TAG, "sMessage:" + sMessage);

            if (message.getData().isEmpty()) {
                sendNotification(sTitle, sMessage, null);
            } else {
                sendNotification(sTitle, sMessage, message.getData());
            }

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e(TAG, "onNewToken: " + token);
    }

    private void sendNotification(String sTitle, String sMessage, Map<String, String> data) {

        if (data != null) {

            Log.e(TAG, "data: " + data.toString());

            String sParam1 = data.get("param1");
            if (sParam1 != null) {
                Log.e(TAG, "sParam1:" + sParam1);
            }

            String sParam2 = data.get("param2");
            if (sParam2 != null) {
                Log.e(TAG, "sParam2:" + sParam2);
            }
        }

        Intent intent = new Intent(this, PushActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.channel_id));
        builder.setSmallIcon(R.drawable.ic_push_icons);
        builder.setContentTitle(sTitle);
        builder.setContentText(sMessage);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(sMessage));
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        int notificationId = new Random().nextInt();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(notificationId, builder.build());
    }
}
*/