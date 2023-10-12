package com.daeseong.permission33sdk_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Permission7Activity extends AppCompatActivity {

    private static final String TAG = Permission7Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String[]> requestPermissions;

    /*
    [일회성 권한]
    안드로이드 11 이상 : 위치, 카메라 접근권한 요청 시 이번만 허용 옵션 추가
    안드로이드 10 이하 : 이번만 허용 옵션 없음
    */
    //안드로이드 11 이상부터는 허용, 이번만 허용, 허용 안됨
    //안드로이드 10 이하는 허용, 거부

    private static final String[] PERMISSIONS = new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission7);

        init();

        initPermissionsLauncher();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result = false;
                for (String permission : PERMISSIONS) {
                    result = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
                    if (!result) {
                        break;
                    }
                }

                if(result){
                    Toast.makeText(Permission7Activity.this, "이미 권한 소유", Toast.LENGTH_SHORT).show();
                } else {
                    requestPermissions.launch(PERMISSIONS);
                }

            }
        });
    }

    private void initPermissionsLauncher() {

        requestPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            Toast.makeText(Permission7Activity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        });
    }

    private void init() {

        for (String permission : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(Permission7Activity.this, permission + " 미허용 상태", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Permission7Activity.this, permission + " 허용 상태", Toast.LENGTH_SHORT).show();
            }
        }
    }
}