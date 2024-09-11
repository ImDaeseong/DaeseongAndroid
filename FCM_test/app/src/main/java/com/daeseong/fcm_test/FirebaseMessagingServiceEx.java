package com.daeseong.fcm_test;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import java.util.Random;

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
