package com.daeseong.permission33sdk_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

public class Permission9Activity extends AppCompatActivity {

    private static final String TAG = Permission9Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String[]> permissResultLauncher;

    private static final String[] PERMISSIONS = new String[]{android.Manifest.permission.CAMERA};
    private static final String[] PERMISSIONS33 = new String[]{android.Manifest.permission.CAMERA, Manifest.permission.POST_NOTIFICATIONS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission9);

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

        permissResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                permissions -> {

                    for (String permission : permissions.keySet()) {

                        boolean isGranted = permissions.get(permission);
                        if (isGranted) {

                            Log.e(TAG, permission + " 권한 소유");

                        } else {

                            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                                Log.e(TAG, "앱 설정창에서 권한 체크 해야함");

                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                startActivity(intent);

                            } else {

                                Log.e(TAG, permission + " 권한이 거부됨");
                            }
                        }
                    }
                });

    }

    private void init() {

        String[] permissions = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) ? PERMISSIONS33 : PERMISSIONS;

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {

                Log.e(TAG, permission + " 권한 없음");
                permissResultLauncher.launch(new String[]{permission});

            } else {

                Log.e(TAG,  "권한 이미 있음");
            }
        }
    }
}