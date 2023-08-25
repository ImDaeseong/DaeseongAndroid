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

import java.util.Arrays;
import java.util.Map;

public class Permission5Activity extends AppCompatActivity {

    private static final String TAG = Permission5Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String[]> requestPermissions;

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String[] PERMISSIONS33 = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.POST_NOTIFICATIONS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission5);

        initPermissionsLauncher();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    checkAndRequestPermissions(PERMISSIONS33);
                } else {
                    checkAndRequestPermissions(PERMISSIONS);
                }
            }
        });
    }

    private void checkAndRequestPermissions(String[] permissions) {

        boolean allPermissionsGranted = true;

        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (!allPermissionsGranted) {
            requestPermissions.launch(permissions);
        } else {
            Log.e(TAG, "권한이 이미 허용되었습니다.");
        }
    }

    private void initPermissionsLauncher() {

        requestPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            try {

                for (Map.Entry<String, Boolean> entry : result.entrySet()) {

                    String permission = entry.getKey();
                    Boolean isGranted = entry.getValue();

                    //첫번째 방식
                    /*
                    if (Arrays.asList(PERMISSIONS).contains(permission)) {
                        if (isGranted) {
                            Log.e(TAG, permission + " 권한 허용");
                        } else {
                            Log.e(TAG, permission + " 권한 거부");
                        }
                    } else if (Arrays.asList(PERMISSIONS33).contains(permission)) {
                        if (isGranted) {
                            Log.e(TAG, permission + " 권한 허용");
                        } else {
                            Log.e(TAG, permission + " 권한 거부");
                        }
                    }
                    */

                    if (Manifest.permission.CAMERA.equals(permission)) {
                        if (isGranted) {
                            Log.e(TAG, "CAMERA 권한 허용");
                        } else {
                            Log.e(TAG, "CAMERA 권한 거부");
                        }
                    } else if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission)) {
                        if (isGranted) {
                            Log.e(TAG, "READ_EXTERNAL_STORAGE 권한 허용");
                        } else {
                            Log.e(TAG, "READ_EXTERNAL_STORAGE 권한 거부");
                        }
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                        if (isGranted) {
                            Log.e(TAG, "WRITE_EXTERNAL_STORAGE 권한 허용");
                        } else {
                            Log.e(TAG, "WRITE_EXTERNAL_STORAGE 권한 거부");
                        }
                    } else if (Manifest.permission.READ_MEDIA_IMAGES.equals(permission)) {
                        if (isGranted) {
                            Log.e(TAG, "READ_MEDIA_IMAGES 권한 허용");
                        } else {
                            Log.e(TAG, "READ_MEDIA_IMAGES 권한 거부");
                        }
                    } else if (Manifest.permission.READ_MEDIA_VIDEO.equals(permission)) {
                        if (isGranted) {
                            Log.e(TAG, "READ_MEDIA_VIDEO 권한 허용");
                        } else {
                            Log.e(TAG, "READ_MEDIA_VIDEO 권한 거부");
                        }
                    } else if (Manifest.permission.READ_MEDIA_AUDIO.equals(permission)) {
                        if (isGranted) {
                            Log.e(TAG, "READ_MEDIA_AUDIO 권한 허용");
                        } else {
                            Log.e(TAG, "READ_MEDIA_AUDIO 권한 거부");
                        }
                    } else if (Manifest.permission.POST_NOTIFICATIONS.equals(permission)) {
                        if (isGranted) {
                            Log.e(TAG, "POST_NOTIFICATIONS 권한 허용");
                        } else {
                            Log.e(TAG, "POST_NOTIFICATIONS 권한 거부");
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}