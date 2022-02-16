package com.daeseong.appwidgetprovider_test;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

public class NewAppWidget extends AppWidgetProvider {

    private static final String TAG = NewAppWidget.class.getSimpleName();

    public static final String CRT_ITEM = "com.daeseong.appwidgetprovider_test.CREATE_ITEM";
    public static final String NOR_ITEM = "com.daeseong.appwidgetprovider_test.NORMAL_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setImageViewResource(R.id.appwidget_img, R.drawable.qr);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_img, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
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

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction() != null) {

            if (intent.getAction().equals(CRT_ITEM)) {

                String sMessage = intent.getStringExtra(CRT_ITEM);
                setQRCode(sMessage, context);

            } else if (intent.getAction().equals(NOR_ITEM)) {

                setQRCode(context);
            }
        }
    }

    private void setQRCode(String sMessage, Context context){

        AppWidgetManager appWidgeManger = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        /*
        //이미지 변경 여부를 확인 하기 위해
        if(sMessage.isEmpty()){

            remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr);
        } else {

            remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qrview);
        }
        */

        try {

            Bitmap bitmap = QRinfo.CreateQRrcode(sMessage, 220, 220);
            if (bitmap != null) {
                remoteViews.setImageViewBitmap(R.id.appwidget_img, bitmap);
            } else {
                remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr);
            }

        }catch (Exception ex){
            remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr);
        }

        appWidgeManger.updateAppWidget(new ComponentName(context, NewAppWidget.class), remoteViews);
    }

    private void setQRCode(Context context){
        AppWidgetManager appWidgeManger = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr);
        appWidgeManger.updateAppWidget(new ComponentName(context, NewAppWidget.class), remoteViews);
    }
}