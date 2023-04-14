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
import java.util.Random;

public class FirebaseMessagingServiceEx extends FirebaseMessagingService {

    private static final String TAG = FirebaseMessagingServiceEx.class.getSimpleName();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        try {

            if (message.getData().size() > 0) {
                Log.e(TAG, "getData: " + message.getData());
            }

            if (message.getNotification() != null) {
                Log.e(TAG, "getTitle:" + message.getNotification().getTitle());
                Log.e(TAG, "getBody:" + message.getNotification().getBody());
                sendNotification(message.getNotification().getTitle(), message.getNotification().getBody() );
            }

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private void sendNotification(String sTitle, String sMessage) {

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
