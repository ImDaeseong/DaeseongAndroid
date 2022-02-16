package com.daeseong.appwidgetprovider_test;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class NewAppWidget2 extends AppWidgetProvider {

    private static final String TAG = NewAppWidget2.class.getSimpleName();

    public static final String ACTION_BTN = "CLICKBUTTON";
    public static final String ACTION_TXT = "CLICKTEXT";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget2);

        views.setImageViewResource(R.id.appwidget_img, R.drawable.qr);
        views.setTextViewText(R.id.appwidget_tv, "Qr 위젯");;

        Intent intent1 = new Intent(context, NewAppWidget2.class);
        intent1.setAction(ACTION_BTN);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context,0, intent1, 0);
        views.setOnClickPendingIntent(R.id.appwidget_img, pendingIntent1);

        Intent intent2 = new Intent(context, NewAppWidget2.class);
        intent2.setAction(ACTION_TXT);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context,0, intent2, 0);
        views.setOnClickPendingIntent(R.id.appwidget_tv, pendingIntent2);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction() != null) {

            if (intent.getAction().equals(ACTION_BTN) || intent.getAction().equals(ACTION_TXT)) {

                if (MainActivity.getMainActivity() == null) {

                    final Intent activity = new Intent(context, MainActivity.class);
                    activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(activity);

                } else {

                    MainActivity.getMainActivity().func_Main();
                }
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}