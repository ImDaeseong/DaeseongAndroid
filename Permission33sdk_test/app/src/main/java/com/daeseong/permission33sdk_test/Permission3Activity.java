package com.daeseong.permission33sdk_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Permission3Activity extends AppCompatActivity {

    private static final String TAG = Permission3Activity.class.getSimpleName();

    private Button button1;

    public ActivityResultLauncher<String> permissResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission3);

        initResultPermission();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sdk 33 이상
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    if (ActivityCompat.checkSelfPermission(Permission3Activity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                        permissResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                    } else {
                        Log.e(TAG, "POST_NOTIFICATIONS 권한 소유");
                    }

                } else {

                    Log.e(TAG, "sdk 33 이하");
                }
            }
        });
    }

    private void initResultPermission() {

        permissResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                result -> {

                    if (result) {
                        Log.e(TAG, "POST_NOTIFICATIONS 권한 소유");
                    } else {
                        Log.e(TAG, "POST_NOTIFICATIONS 권한 미소유");
                    }
                });
    }
}