package com.im.daeseong.bannerviewpager_test;

import android.app.Application;

/**
 * Created by Daeseong on 2018-02-09.
 */

public class MyApplication extends Application {

    private static MyApplication application;
    public static MyApplication getApplication(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

    }
}
