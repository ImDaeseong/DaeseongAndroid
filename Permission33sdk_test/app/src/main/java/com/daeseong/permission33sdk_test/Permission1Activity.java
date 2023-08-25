package com.daeseong.permission33sdk_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.Manifest;
import java.io.IOException;

public class Permission1Activity extends AppCompatActivity {

    private static final String TAG = Permission1Activity.class.getSimpleName();

    private ImageView imageView1;
    private Button button1, button2;

    public ActivityResultLauncher<Intent> activityResultLauncherGallery;
    public ActivityResultLauncher<Intent> activityResultLauncherCamera;

    private Bitmap bitmap;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission1);

        initResultGallery();
        initResultCamera();

        imageView1 = findViewById(R.id.imageView1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    if (ActivityCompat.checkSelfPermission(Permission1Activity.this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(Permission1Activity.this, new String[]{Manifest.permission.READ_MEDIA_IMAGES }, 1);

                    } else {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        activityResultLauncherGallery.launch(intent);
                    }

                } else {

                    if (ActivityCompat.checkSelfPermission(Permission1Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(Permission1Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE }, 1);

                    } else {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        activityResultLauncherGallery.launch(intent);
                    }
                }

            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(Permission1Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Permission1Activity.this, new String[]{Manifest.permission.CAMERA}, 2);

                } else {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activityResultLauncherCamera.launch(intent);

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncherGallery.launch(intent);
            }

        } else if (requestCode == 2) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncherCamera.launch(intent);
            }
        }
    }

    private void initResultGallery() {

        activityResultLauncherGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getData() != null && result.getResultCode() == RESULT_OK) {

                        uri = result.getData().getData();

                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageView1.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initResultCamera() {

        activityResultLauncherCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getData() != null && result.getResultCode() == RESULT_OK) {
                        Bundle bundle = result.getData().getExtras();
                        bitmap = (Bitmap) bundle.get("data");
                        imageView1.setImageBitmap(bitmap);
                    }
                });
    }

}