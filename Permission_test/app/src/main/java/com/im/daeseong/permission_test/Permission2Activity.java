package com.im.daeseong.permission_test;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.permission_test.Util.PermissionUtilA;

public class Permission2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if( checkSelfPermission() ) {

            //권한이 있으면 메인으로
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Permission2Activity.this, MainActivity.class);
                    Permission2Activity.this.startActivity(intent);
                    Permission2Activity.this.finish();
                }
            }, 1000);

        } else {

            //권한이 없으면 권한 요청
            ActivityCompat.requestPermissions(this, PermissionUtilA.PERMISSIONS, PermissionUtilA.RESULT_CODE);
        }

    }

    //정의된 모든 권한 체크
    private boolean checkSelfPermission() {

        for( String permission : PermissionUtilA.PERMISSIONS ) {
            if( ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return  true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Log.e("Permission2Activity", "requestCode:" + requestCode);

        for( int result : grantResults ) {

            //거부된 권한이 있는 경우 다시
            if ( result == PackageManager.PERMISSION_DENIED ) {
                ActivityCompat.requestPermissions(this, PermissionUtilA.PERMISSIONS, PermissionUtilA.RESULT_CODE);
                return;
            }
        }

    }

}
