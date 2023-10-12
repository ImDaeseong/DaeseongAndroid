package com.daeseong.permission33sdk_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Permission6Activity extends AppCompatActivity {

    private static final String TAG = Permission6Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String[]> requestPermissions;

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String[] PERMISSIONS33 = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.POST_NOTIFICATIONS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission6);

        initPermissionsLauncher();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e(TAG, "필수 권한만 체크하고 선택 권한은 무시");

                boolean result = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    result = checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;

                } else {

                    for (String permission : PERMISSIONS) {
                        result = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
                        if(!result) {
                            break;
                        }
                    }
                }

                if(result){
                    Log.e(TAG, "이미 권한 소유");
                } else {
                    //Log.e(TAG, "권한 체크");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        requestPermissions.launch(PERMISSIONS33);
                    } else {
                        requestPermissions.launch(PERMISSIONS);
                    }
                }

            }
        });
    }

    private void initPermissionsLauncher() {

        requestPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            boolean bPermissions = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                if (Boolean.TRUE.equals(result.get(Manifest.permission.READ_PHONE_STATE))) {
                    bPermissions = true;
                }

            } else {

                if (Boolean.TRUE.equals(result.get(Manifest.permission.READ_PHONE_STATE)) && Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
                    bPermissions = true;
                }
            }

            if (bPermissions) {
                Log.e(TAG, "전체 퍼미션 허용");
            } else {
                Log.e(TAG, "퍼미션 요청 거절 상태");
            }

        });

    }

}