package com.daeseong.broadcastreceiver_test;

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

    @Override
    public void onCreate() {
        super.onCreate();

        initBroadcastReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        DestoryBroadcastReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initBroadcastReceiver(){

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                    Log.e(TAG, "폰화면 꺼짐" );

                    Intent item = new Intent("com.daeseong.Screen");
                    item.putExtra("type", 1);
                    item.putExtra("screen", "off");
                    context.sendBroadcast(item);

                } else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

                    Log.e(TAG, "폰화면 켜짐" );

                    Intent item = new Intent("com.daeseong.Screen");
                    item.putExtra("type", 1);
                    item.putExtra("screen", "on");
                    context.sendBroadcast(item);

                }
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void DestoryBroadcastReceiver(){
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}

