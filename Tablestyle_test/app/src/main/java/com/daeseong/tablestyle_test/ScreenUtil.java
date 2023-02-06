package com.daeseong.tablestyle_test;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenUtil {

    public static int getScreenHeight(Activity activity){
        try{
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int height = metrics.heightPixels;
            return height;
        } catch(Exception e) {
            return 0;
        }
    }

    public static int getScreenWidth(Activity activity){
        try {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            return width;
        } catch (Exception e) {
            return 0;
        }
    }
}
