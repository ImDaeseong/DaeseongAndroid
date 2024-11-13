package com.im.daeseong.Shortcut_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkShortCut();
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShortCutUtil shortCutUtil = new ShortCutUtil(MainActivity.this);
                shortCutUtil.checkShortCut();
            }
        });
    }

    private void checkShortCut() {

        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            Log.e(TAG, "홈 화면에 바로가기 지원");
            createShortCut();
        } else {
            Log.e(TAG, "홈 화면에 바로가기 미지원");
        }
    }

    private void createShortCut() {

        String sPackageName = "com.im.daeseong.Shortcut_test";
        String sID = "testID";
        String sLabel = "나의앱 바로가기";
        String sShortcutId = sPackageName + "_" + sID;

        // 실행할 인텐트 생성
        Intent intent = new Intent(this, SplashActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("pkg", sPackageName);
        intent.putExtra("userId", sID);

        // 바로가기 정보 생성
        ShortcutInfoCompat shortcutInfo = new ShortcutInfoCompat.Builder(this, sShortcutId)
                .setIntent(intent)
                .setShortLabel(sLabel)
                .setLongLabel(sLabel)
                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground))
                .build();

        // 바로가기 생성 요청
        ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null);
    }

}