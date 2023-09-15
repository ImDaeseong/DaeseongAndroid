package com.daeseong.okhttpclient_test;

import android.app.Application;
import android.content.res.Configuration;
import androidx.annotation.NonNull;

public class OKHttpClientApplication extends Application {

    private static final String TAG = OKHttpClientApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
