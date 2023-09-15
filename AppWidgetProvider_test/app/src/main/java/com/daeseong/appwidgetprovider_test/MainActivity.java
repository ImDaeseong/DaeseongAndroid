package com.daeseong.appwidgetprovider_test;

import androidx.appcompat.app.AppCompatActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = MainActivity.class.getSimpleName();

    private static MainActivity mainActivity;
    public static MainActivity getMainActivity(){
        return  mainActivity;
    }
    private static void setMainActivity(MainActivity mainActivity){
        MainActivity.mainActivity = mainActivity;
    }

    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setMainActivity(this);

        //바탕화면 위젯생성
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //바로가기 생성
                //setWidgetShortcut1(MainActivity.this);
                setWidgetShortcut2(MainActivity.this);
            }
        });

        //qr 생성
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, NewAppWidget.class);
                intent.setAction(NewAppWidget.CRT_ITEM);
                intent.putExtra(NewAppWidget.CRT_ITEM, "0123456789");
                MainActivity.this.sendBroadcast(intent);
            }
        });

        //qr 초기화
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, NewAppWidget.class);
                intent.setAction(NewAppWidget.NOR_ITEM);
                MainActivity.this.sendBroadcast(intent);
            }
        });
    }

    private void setWidgetShortcut1(Context context) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                AppWidgetManager appWidgetManager = context.getSystemService(AppWidgetManager.class);

                if (appWidgetManager.isRequestPinAppWidgetSupported()) {

                    //위젯 없을 경우에만 추가
                    if (appWidgetManager.getAppWidgetIds(new ComponentName(context, NewAppWidget.class)).length == 0) {

                        ComponentName widgetProvider = new ComponentName(context, NewAppWidget.class);
                        Intent pinnedWidgetCallbackIntent = new Intent(this, MainActivity.class);
                        PendingIntent successCallback = PendingIntent.getBroadcast(this, 0, pinnedWidgetCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                        appWidgetManager.requestPinAppWidget(widgetProvider, null, successCallback);
                    }
                }
            }

        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private void setWidgetShortcut2(Context context) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                AppWidgetManager appWidgetManager = context.getSystemService(AppWidgetManager.class);

                if (appWidgetManager.isRequestPinAppWidgetSupported()) {

                    //위젯 없을 경우에만 추가
                    if (appWidgetManager.getAppWidgetIds(new ComponentName(context, NewAppWidget2.class)).length == 0) {

                        ComponentName widgetProvider = new ComponentName(context, NewAppWidget2.class);
                        Intent pinnedWidgetCallbackIntent = new Intent(this, MainActivity.class);
                        PendingIntent successCallback = PendingIntent.getBroadcast(this, 0, pinnedWidgetCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                        appWidgetManager.requestPinAppWidget(widgetProvider, null, successCallback);
                    }
                }
            }

        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    public void func_Main(){

        final Intent activity = new Intent(this, MainActivity.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(activity);

        /*
        //현재 상태 유지
        final Intent activity = new Intent(this, MainActivity.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(activity);
        */
    }

}