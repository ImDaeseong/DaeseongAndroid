package com.daeseong.sensormanager_test;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class ScreenOnOffService extends Service {

    private static final String TAG = ScreenOnOffService.class.getSimpleName();

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;

    public ScreenOnOffService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initFilter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        DestoryFilter();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initFilter(){

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                    Intent item = new Intent("com.daeseong.sensormanager_test.Screen");
                    item.putExtra("screen", "폰화면 꺼짐");
                    context.sendBroadcast(item);

                } else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){

                    Intent item = new Intent("com.daeseong.sensormanager_test.Screen");
                    item.putExtra("screen", "폰화면 켜짐");
                    context.sendBroadcast(item);

                }
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void DestoryFilter(){
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
