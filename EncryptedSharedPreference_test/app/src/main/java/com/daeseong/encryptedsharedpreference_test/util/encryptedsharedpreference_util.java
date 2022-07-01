package com.daeseong.encryptedsharedpreference_test.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

public class encryptedsharedpreference_util {

    private static final String TAG = encryptedsharedpreference_util.class.getSimpleName();

    private static final String FILE_NAME = "ShareData";

    private static SharedPreferences EncsharedPreferences = null;

    private static encryptedsharedpreference_util instance;
    public static encryptedsharedpreference_util getInstance(Context context ) {
        if (instance == null) {
            instance = new encryptedsharedpreference_util(context);
        }
        return instance;
    }

    public encryptedsharedpreference_util(Context context) {

        String strMasterkey = null;

        try {

            strMasterkey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        } catch (GeneralSecurityException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        try {

            EncsharedPreferences = EncryptedSharedPreferences.create(
                    FILE_NAME,
                    strMasterkey,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (GeneralSecurityException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void setValue(String sKey, Object oData){

        String sType = oData.getClass().getSimpleName();

        if("String".equals(sType)){
            EncsharedPreferences.edit().putString(sKey, (String)oData).apply();
        }else if("Integer".equals(sType)){
            EncsharedPreferences.edit().putInt(sKey, (Integer)oData).apply();
        }else if("Boolean".equals(sType)){
            EncsharedPreferences.edit().putBoolean(sKey, (Boolean)oData).apply();
        }else if("Float".equals(sType)){
            EncsharedPreferences.edit().putFloat(sKey, (Float)oData).apply();
        }else if("Long".equals(sType)){
            EncsharedPreferences.edit().putLong(sKey, (Long)oData).apply();
        }
    }

    public static Object getValue(String sKey, Object oData){

        String sType = oData.getClass().getSimpleName();

        if("String".equals(sType)){
            return EncsharedPreferences.getString(sKey, (String)oData);
        }else if("Integer".equals(sType)){
            return  EncsharedPreferences.getInt(sKey, (Integer)oData);
        }else if("Boolean".equals(sType)){
            return  EncsharedPreferences.getBoolean(sKey, (Boolean)oData);
        }else if("Float".equals(sType)){
            return  EncsharedPreferences.getFloat(sKey, (Float)oData);
        }else if("Long".equals(sType)){
            return  EncsharedPreferences.getLong(sKey, (Long)oData);
        }
        return null;
    }

    public static void remove(String sKey){
        EncsharedPreferences.edit().remove(sKey).apply();
    }

    public static void clear(){
        EncsharedPreferences.edit().clear().apply();
    }

    public static boolean contains(String sKey){
        return  EncsharedPreferences.contains(sKey);
    }

    public static Map<String, ?> getAll(){
        return EncsharedPreferences.getAll();
    }

}
