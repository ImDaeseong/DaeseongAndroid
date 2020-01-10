package com.daeseong.broadcastreceiver_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        initBroadcastReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DestoryBroadcastReceiver();
    }

    private void initBroadcastReceiver(){

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                    Log.e(TAG, "폰화면 꺼짐" );

                    Intent item = new Intent("com.daeseong.Screen");
                    item.putExtra("type", 2);
                    item.putExtra("screen", "off");
                    context.sendBroadcast(item);

                } else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){

                    Log.e(TAG, "폰화면 켜짐" );

                    Intent item = new Intent("com.daeseong.Screen");
                    item.putExtra("type", 2);
                    item.putExtra("screen", "on");
                    context.sendBroadcast(item);

                }
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void DestoryBroadcastReceiver(){
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
