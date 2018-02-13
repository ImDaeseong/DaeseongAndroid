package com.im.daeseong.newbanner_test;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return instance;
    }

    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        mContext = getApplicationContext();
    }
}
