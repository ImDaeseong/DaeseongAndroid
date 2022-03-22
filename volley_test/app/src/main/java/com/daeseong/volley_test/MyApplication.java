package com.daeseong.volley_test;

import android.app.Application;
import android.content.res.Configuration;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return instance;
    }

    private static RequestQueue requestQueue;
    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
