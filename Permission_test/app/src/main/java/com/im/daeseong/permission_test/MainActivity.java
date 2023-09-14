package com.im.daeseong.permission_test;

import android.content.pm.PackageManager;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.core.app.ActivityCompat;//import android.support.v4.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.permission_test.Util.PermissionUtilA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //카메라 권한만 체크
        PermissionUtilA.isCamera(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Log.e("MainActivity", "requestCode:" + requestCode);

        for( int result : grantResults ) {

            //거부된 권한이 있는 경우 다시
            if ( result == PackageManager.PERMISSION_DENIED ) {
                ActivityCompat.requestPermissions(this, PermissionUtilA.PERMISSIONS, PermissionUtilA.RESULT_CODE);
                return;
            }
        }

    }
}
