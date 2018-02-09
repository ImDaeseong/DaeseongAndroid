package com.im.daeseong.singleton_test.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Screen_util {

    private WindowManager windowManager;

    private static Screen_util instance;
    public static Screen_util getInstance(){
        if( instance == null){
            instance = new Screen_util();
        }
        return instance;
    }

    public void init(Context context){
        windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    }

    public int getScreenWidthPx() {
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public int getScreenHeightPx() {
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
