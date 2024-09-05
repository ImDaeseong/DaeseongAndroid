package com.im.daeseong.sharedpreferences_test.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

public class SharedPreferences_util {

    private static final String FILE_NAME = "ShareData";

    public static void setValue(Context context, String sKey, Object oData){

        String sType = oData.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if("String".equals(sType)){
            editor.putString(sKey, (String)oData);
        }else if("Integer".equals(sType)){
            editor.putInt(sKey, (Integer)oData);
        }else if("Boolean".equals(sType)){
            editor.putBoolean(sKey, (Boolean)oData);
        }else if("Float".equals(sType)){
            editor.putFloat(sKey, (Float)oData);
        }else if("Long".equals(sType)){
            editor.putLong(sKey, (Long)oData);
        }
        editor.commit();
    }

    public static Object getValue(Context context, String sKey, Object oData){

        String sType = oData.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if("String".equals(sType)){
            return  sharedPreferences.getString(sKey, (String)oData);
        }else if("Integer".equals(sType)){
            return  sharedPreferences.getInt(sKey, (Integer)oData);
        }else if("Boolean".equals(sType)){
            return  sharedPreferences.getBoolean(sKey, (Boolean)oData);
        }else if("Float".equals(sType)){
            return  sharedPreferences.getFloat(sKey, (Float)oData);
        }else if("Long".equals(sType)){
            return  sharedPreferences.getLong(sKey, (Long)oData);
        }
        return null;
    }

    public static void remove(Context context, String sKey){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(sKey);
        editor.apply();
    }

    public static void clear(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean contains(Context context, String sKey){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.contains(sKey);
    }

    public static Map<String, ?> getAll(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getAll();
    }

}
