package com.daeseong.alarm_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        //호출후 삭제시 필요
        int nID = intent.getExtras().getInt("alarmID");

        Log.e(TAG, "호출 시간 : " + getTimeDate() + " ID:" + nID);

        //activity에 전달
        Intent iID = new Intent("com.daeseong.alarm_test.ID");
        iID.putExtra("alarmID", nID);
        context.sendBroadcast(iID);

    }

    private static String getTimeDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

}
