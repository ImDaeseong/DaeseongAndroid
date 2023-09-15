package com.im.daeseong.mainui_test;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("TAG", "getVersionName:" + getVersionName(this) + "getVersionCode:" + getVersionCode(this) );
    }

    //매니페스트 버전
    private String getVersionName(Context context){
        String version = "";
        try{
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        }catch (PackageManager.NameNotFoundException e){
        }
        return version;
    }

    //매니페스트 버전 코드
    private int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch(PackageManager.NameNotFoundException e) {
        }
        return versionCode;
    }


}
