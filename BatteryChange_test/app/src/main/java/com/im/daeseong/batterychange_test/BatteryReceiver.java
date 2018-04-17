package com.im.daeseong.batterychange_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BatteryReceiver extends BroadcastReceiver{

    public BatteryListener batteryListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        int batteryLevel = intent.getIntExtra("level", 0);
        batteryListener.onListener(batteryLevel);
    }

    public interface BatteryListener{
        void onListener(int batteryLevel);
    }

    public void setBatteryListener(BatteryListener batteryListener){
        this.batteryListener = batteryListener;
    }
}
