package com.daeseong.devicepolicymanager_test;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

public class DeviceAdminReceiverEx extends DeviceAdminReceiver {

    private static final String TAG = DeviceAdminReceiverEx.class.getSimpleName();

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);

        Log.e(TAG, "onReceive:" + intent.getAction());
    }

    @Override
    public void onLockTaskModeEntering(@NonNull Context context, @NonNull Intent intent, @NonNull String pkg) {
        super.onLockTaskModeEntering(context, intent, pkg);

        Log.e(TAG, "onLockTaskModeEntering:" + pkg);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {

        Log.e(TAG, "DeviceAdminReceiverEx DEVICE_ADMIN_ENABLED");
    }

    @Override
    public void onDisabled(Context context, Intent intent) {

        Log.e(TAG, "DeviceAdminReceiverEx DEVICE_ADMIN_DISABLED");
    }
}
