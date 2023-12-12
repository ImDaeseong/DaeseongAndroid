package com.daeseong.createchooser_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3, button4, button5, button6;

    private ActivityResultLauncher<String[]> requestPermissions;

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String[] PERMISSIONS33 = new String[]{Manifest.permission.READ_MEDIA_IMAGES};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPermissionsLauncher();

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTitle = "제목1";
                String slink = "https://m.naver.com";
                share_Utils.shareLink(MainActivity.this, sTitle, slink);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTitle = "제목2";
                String slink = "https://m.naver.com";
                String sText = "사용 설명과 링크 정보1\n사용 설명과 링크 정보2\n" + slink;
                share_Utils.shareText(MainActivity.this, sTitle, sText);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTitle = "제목3";
                Uri imgUri = img_Utils.GetAsset(MainActivity.this, "a.png");
                share_Utils.shareImage(MainActivity.this, sTitle, imgUri);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTitle = "제목4";
                String slink = "https://m.naver.com";
                String sText = "사용 설명과 링크 정보1\n사용 설명과 링크 정보2\n" + slink;
                Uri imgUri = img_Utils.GetAsset(MainActivity.this, "a.png");
                share_Utils.shareMultiText(MainActivity.this, sTitle, sText, imgUri);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String sImgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
                    img_Utils.GetObservableBitmap(sImgUrl)
                            .subscribe(bBitmap -> {

                                if (bBitmap != null) {
                                    Uri imgUri = img_Utils.GetUri(MainActivity.this, bBitmap);
                                    String sTitle = "제목5";
                                    String slink = "https://m.naver.com";
                                    String sText = "사용 설명과 링크 정보1\n사용 설명과 링크 정보2\n" + slink;
                                    share_Utils.shareMultiText(MainActivity.this, sTitle, sText, imgUri);
                                }
                            });

                } catch (Exception ex) {
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSettings(1);
            }
        });

        checkPermissions();
    }

    private void initPermissionsLauncher() {

        requestPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            boolean bImage;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bImage = Boolean.TRUE.equals(result.get(Manifest.permission.READ_MEDIA_IMAGES));
            } else {
                bImage = Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE));
            }

            if (bImage) {
                Log.e(TAG, "PERMISSIONS 권한 소유");
            } else {
                Log.e(TAG, "PERMISSIONS 권한 미소유");
            }
        });
    }

    private void checkPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

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
    }

    private void openSettings(int settingType) {
        Intent intent = null;

        switch (settingType) {
            case 1:
                // 전체 시스템 설정
                intent = new Intent(Settings.ACTION_SETTINGS);
                break;

            case 2:
                // 무선 및 네트워크 설정
                intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                break;

            case 3:
                // 블루투스 설정
                intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                break;

            case 4:
                // 위치 서비스 설정
                intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                break;

            case 5:
                // 음성 입력 설정
                intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                break;

            case 6:
                // 화면 시간 설정
                intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                break;

            case 7:
                // 언어 및 입력 메뉴
                intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                break;

            case 8:
                // 일반 메뉴
                intent = new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

}