package com.im.daeseong.permission_test.Util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;//import android.support.v4.app.ActivityCompat;

public class PermissionUtilA {

    public static final int RESULT_CODE = 1;

    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    //마시멜로 버전보다 클 경우
    public static boolean isMarshmellow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        }
        return false;
    }

    //카메라 권한 체크
    public static boolean isCamera(Activity activity) {

        //마시멜로 이전 버전은 권한 체크 필요 없음
        if(!isMarshmellow()) {
            return true;
        }

        //카메라 권한 있는 경우
        int nResult = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if ( nResult == PackageManager.PERMISSION_GRANTED ) {
            return true;
        }

        //권한이 필요한 이유를 설명
        if ( ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA) ) {

            //권한 요청
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, RESULT_CODE);

        } else {

            //권한 요청
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, RESULT_CODE);
        }

        return false;
    }

    //쓰기 권한 체크
    public static boolean isWriteStorage(Activity activity) {

        //마시멜로 이전 버전은 권한 체크 필요 없음
        if(!isMarshmellow()) {
            return true;
        }

        //카메라 권한 있는 경우
        int nResult = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if ( nResult == PackageManager.PERMISSION_GRANTED ) {
            return true;
        }

        //권한이 필요한 이유를 설명
        if ( ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {

            //권한 요청
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_CODE);

        } else {

            //권한 요청
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_CODE);
        }

        return false;
    }

}
