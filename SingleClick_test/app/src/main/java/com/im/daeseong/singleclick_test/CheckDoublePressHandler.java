package com.im.daeseong.singleclick_test;

import android.util.Log;

public class CheckDoublePressHandler {

    private static final String TAG = CheckDoublePressHandler.class.getSimpleName();

    public static long lastTime = 0;

    private static volatile CheckDoublePressHandler instance = null;;
    public static CheckDoublePressHandler getInstance() {

        if (instance == null) {
            synchronized (CheckDoublePressHandler.class) {
                if (instance == null) {
                    instance = new CheckDoublePressHandler();
                }
            }
        }
        return instance;
    }

    private CheckDoublePressHandler() {
    }

    public static boolean isDoubleClick() {
        if (System.currentTimeMillis() - lastTime <= 500) {
            //Log.e(TAG , "onCheckDoublePressed");
            return true;
        }
        lastTime = System.currentTimeMillis();
        return false;
    }

}
