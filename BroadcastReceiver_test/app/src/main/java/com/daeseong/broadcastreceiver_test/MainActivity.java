package com.daeseong.broadcastreceiver_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBroadcastReceiver();

        startService(new Intent(MainActivity.this, ScreenOnOffService.class));
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

                int ntype = intent.getExtras().getInt("type");
                String sOn = intent.getExtras().getString("screen");

                Log.e(TAG, String.valueOf(ntype).toString() + " " + sOn);

                SendBroadcast();
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.daeseong.Screen");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(broadcastReceiver, intentFilter);
        }

    }

    private void DestoryBroadcastReceiver(){
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    private void SendBroadcast(){
        Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
        intent.setAction("android.intent.action.MyMessage");
        sendBroadcast(intent);
    }
}
