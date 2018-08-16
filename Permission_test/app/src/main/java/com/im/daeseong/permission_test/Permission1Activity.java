package com.im.daeseong.permission_test;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.im.daeseong.permission_test.Util.PermissionUtil;

public class Permission1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission1);

        if ( PermissionUtil.checkSelfPermission(this, Manifest.permission.CAMERA) &&
             PermissionUtil.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
             PermissionUtil.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
             PermissionUtil.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) ) {

            //권한이 있으면 메인으로
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Permission1Activity.this, MainActivity.class);
                    Permission1Activity.this.startActivity(intent);
                    Permission1Activity.this.finish();
                }
            }, 1000);

        } else  {

            //권한이 없으면 권한 요청
            PermissionUtil.requestPermissions(this);
        }

    }

    //권한 요청시 응답
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

       if( requestCode == PermissionUtil.RESULT_CODE) {

           if( PermissionUtil.checkgranted(grantResults) ) {

               //권한이 있으면 메인으로
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent intent = new Intent(Permission1Activity.this, MainActivity.class);
                       Permission1Activity.this.startActivity(intent);
                       Permission1Activity.this.finish();
                   }
               }, 1000);

           } else {

               //권한이 없으면 종료
               Permission1Activity.this.finish();
           }

       } else {

           super.onRequestPermissionsResult(requestCode, permissions, grantResults);

       }

    }
}
