package com.im.daeseong.singleton_test.util;

public class StringLib {

    private static StringLib instance;
    public static StringLib getInstance(){
        if( instance == null){
            instance = new StringLib();
        }
        return instance;
    }

    public boolean isBlank(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public String getSubString(String str, int max) {
        if (str != null && str.length() > max) {
            return str.substring(0, max);
        } else {
            return str;
        }
    }
}
