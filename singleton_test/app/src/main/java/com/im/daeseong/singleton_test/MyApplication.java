package com.im.daeseong.singleton_test;

import android.app.Application;
import com.im.daeseong.singleton_test.Db.DbHandler;
import com.im.daeseong.singleton_test.util.Screen_util;
import com.im.daeseong.singleton_test.util.SharedPreferences_util;

public class MyApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences_util.getInstance().init(this);

        DbHandler.getInstance().init(this);

        Screen_util.getInstance().init(this);

    }
}
