package com.daeseong.broadcastreceiver_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = MyBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "onReceive:" + intent.getAction());

        if(intent.getAction().equals("android.intent.action.MyMessage")){

            Intent newIntent = new Intent(context, Main1Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(newIntent);
        }

    }
}
