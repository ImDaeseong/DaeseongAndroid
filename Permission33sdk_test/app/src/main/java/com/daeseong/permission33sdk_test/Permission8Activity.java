package com.daeseong.permission33sdk_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Permission8Activity extends AppCompatActivity {

    private static final String TAG = Permission8Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String> permissResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission8);

        initPermissionsLauncher();

        init();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initPermissionsLauncher() {

        permissResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                result -> {

                    if (result) {
                        Log.e(TAG, "POST_NOTIFICATIONS 권한 소유");
                    } else {

                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.POST_NOTIFICATIONS) ) {

                            Log.e(TAG, "앱 설정창에서 권한 체크 해야함");

                            //패키지명에 해당하는 앱만 설정
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.fromParts("package", getPackageName(), null));
                            startActivity(intent);

                            //전체 목록 호출
                            //startActivity(new Intent(Settings.ACTION_SETTINGS));

                        } else {

                            Log.e(TAG, "POST_NOTIFICATIONS 권한 체크 거부");
                        }
                    }
                });
    }

    private void init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                Log.e(TAG, "POST_NOTIFICATIONS 권한 소유");
            }

        } else {
            Log.e(TAG, "sdk 33 이하");
        }
    }

}