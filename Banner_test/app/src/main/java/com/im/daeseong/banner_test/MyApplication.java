package com.im.daeseong.banner_test;

import android.app.Application;
import android.content.res.Configuration;

public class MyApplication  extends Application{

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Banner_util.getInstance().setBanner1();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
