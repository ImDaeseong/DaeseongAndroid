package com.daeseong.fcm_test;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIdServiceEx extends FirebaseInstanceIdService {

    private static final String TAG = FirebaseInstanceIdServiceEx.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String Token = FirebaseInstanceId.getInstance().getToken();
        if(isNullOrEmpty(Token)){
            Log.d(TAG, "not token = " + Token);
        }else {
            Log.d(TAG, "token = " + Token);
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
