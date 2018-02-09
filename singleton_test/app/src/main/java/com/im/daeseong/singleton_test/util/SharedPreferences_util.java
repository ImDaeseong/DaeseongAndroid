package com.im.daeseong.singleton_test.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


public class SharedPreferences_util {

    private static final String FILE_NAME = "ShareData";
    private SharedPreferences sharedPreferences;

    private static SharedPreferences_util instance;
    public static SharedPreferences_util getInstance(){
        if( instance == null){
            instance = new SharedPreferences_util();
        }
        return instance;
    }

    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setValue(String sKey, Object oData){

        String sType = oData.getClass().getSimpleName();
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

    public Object getValue(String sKey, Object oData){

        String sType = oData.getClass().getSimpleName();
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

    public void remove(String sKey){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(sKey);
        editor.apply();
    }

    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean contains(String sKey){
        return  sharedPreferences.contains(sKey);
    }

    public Map<String, ?> getAll(){
        return sharedPreferences.getAll();
    }

}
