package com.im.daeseong.uidrawer;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static int nScreenWidth;
    public static int getScreenWidth(){ return nScreenWidth; }

    private static int nScreenHeight;
    public static int getScreenHeight(){ return nScreenHeight; }

    private static Context mContext;
    public static Context getAppContext(){
        return mContext;
    }

    private static MyApplication mInstance;
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        nScreenWidth = getResources().getDisplayMetrics().widthPixels;
        nScreenHeight = getResources().getDisplayMetrics().heightPixels;

        mInstance = this;
        mContext = getApplicationContext();
    }

}
