package com.im.daeseong.permission_test.Util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;//import android.support.v4.app.ActivityCompat;

public class PermissionUtil {

    public static final int RESULT_CODE = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    //권한 체크
    public static boolean checkSelfPermission(Activity activity, String permission) {
        int nResult = ActivityCompat.checkSelfPermission(activity, permission);
        if ( nResult == PackageManager.PERMISSION_GRANTED ) {
            return true;
        }
        return false;
    }

    //권한 요청
    public static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, PERMISSIONS, RESULT_CODE);
    }

    //권한 요청에 대한 승인 확인
    public static boolean checkgranted(int[] nResults ) {
        if(nResults.length < 1 ) {
            return false;
        }

        for( int result:nResults ) {
            if ( result != PackageManager.PERMISSION_GRANTED ) {
                return  false;
            }
        }
        return true;
    }

}
