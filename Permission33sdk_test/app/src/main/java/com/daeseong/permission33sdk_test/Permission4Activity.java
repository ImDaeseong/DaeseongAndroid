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
import java.util.Map;

public class Permission4Activity extends AppCompatActivity {

    private static final String TAG = Permission4Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String[]> permissResultLauncher;

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final String[] PERMISSIONS33 = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission4);

        initResultPermission();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                        permissResultLauncher.launch(PERMISSIONS33);
                    } else {
                        Log.e(TAG, "이미 권한 소유");
                    }

                } else {

                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        permissResultLauncher.launch(PERMISSIONS);
                    } else {
                        Log.e(TAG, "이미 권한 소유");
                    }
                }
            }
        });
    }

    private void initResultPermission() {

        permissResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {

                    try {

                        for (final Map.Entry<String, Boolean> entry: result.entrySet()) {

                            String permission = entry.getKey();
                            Boolean isGranted = entry.getValue();

                            if (isGranted) {
                                Log.e(TAG, permission);
                            } else {
                                Log.e(TAG, permission + " 권한 미소유");
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}