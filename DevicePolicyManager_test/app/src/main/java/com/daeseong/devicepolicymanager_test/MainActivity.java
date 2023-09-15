package com.daeseong.devicepolicymanager_test;

import androidx.appcompat.app.AppCompatActivity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2;

    private ComponentName componentName;
    private DevicePolicyManager devicePolicyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EnableAdmin();
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisableAdmin();
            }
        });


        //관리자 권한 추가
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, DeviceAdminReceiverEx.class);

        /*
        EnableAdmin();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },100);
        */
    }

    private void EnableAdmin() {

        boolean bAdmin = devicePolicyManager.isAdminActive(componentName);
        if (bAdmin) {

            //화면 잠금
            devicePolicyManager.lockNow();

        } else {

            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "안전한 환경을 위해 실행 버튼을 클릭해주세요.");
            startActivity(intent);
        }
    }

    private void DisableAdmin() {

        boolean bAdmin = devicePolicyManager.isAdminActive(componentName);
        if (bAdmin) {
            devicePolicyManager.removeActiveAdmin(componentName);
        }
    }
}
