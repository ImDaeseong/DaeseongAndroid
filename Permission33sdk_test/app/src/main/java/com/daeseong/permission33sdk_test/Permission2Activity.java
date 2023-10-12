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

public class Permission2Activity extends AppCompatActivity {

    private static final String TAG = Permission2Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String[]> requestPermissions;

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final String[] PERMISSIONS33 = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission2);

        initPermissionsLauncher();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean bPermissResult = false;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    for (String permission : PERMISSIONS33) {
                        bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
                        if(!bPermissResult) {
                            break;
                        }
                    }

                    if(!bPermissResult) {
                        requestPermissions.launch(PERMISSIONS33);
                    } else {
                        Log.e(TAG, "PERMISSIONS33 권한 소요");
                    }

                } else {

                    for (String permission : PERMISSIONS) {
                        bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
                        if(!bPermissResult) {
                            break;
                        }
                    }

                    if(!bPermissResult) {
                        requestPermissions.launch(PERMISSIONS);
                    } else {
                        Log.e(TAG, "PERMISSIONS 권한 소요");
                    }
                }

            }
        });
    }

    private void initPermissionsLauncher() {

        requestPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            boolean bCamera = false;
            boolean bImage = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                bCamera = Boolean.TRUE.equals(result.get(Manifest.permission.CAMERA));
                bImage = Boolean.TRUE.equals(result.get(Manifest.permission.READ_MEDIA_IMAGES));

            } else {

                bCamera = Boolean.TRUE.equals(result.get(Manifest.permission.CAMERA));
                bImage = Boolean.TRUE.equals(result.get(Manifest.permission.READ_EXTERNAL_STORAGE));
            }

            if (bCamera && bImage) {
                Log.e(TAG, "PERMISSIONS 권한 소유");
            } else {
                Log.e(TAG, "PERMISSIONS 권한 미소유");
            }

        });
    }
}