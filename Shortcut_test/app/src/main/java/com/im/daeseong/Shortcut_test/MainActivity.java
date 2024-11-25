package com.im.daeseong.Shortcut_test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

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
                updateShortcut();
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShortcutInfo> ids = getPinnedShortcuts(MainActivity.this);
                for (ShortcutInfo id : ids) {
                    Log.e(TAG, id.toString());
                }
            }
        });

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( ShortCutUtil.isPinnedShortcuts(MainActivity.this, "testID123")) {

                    Log.e(TAG, "바로가기 이미 존재");

                    ShortCutUtil.checkShortCut(MainActivity.this, getPackageName(), "바로가기", "testID123", "전달할 추가 데이터 업데이트");

                } else {

                    Log.e(TAG, "바로가기 생성");

                    ShortCutUtil.checkShortCut(MainActivity.this, getPackageName(), "바로가기", "testID123", "전달할 추가 데이터");

                }

            }
        });
    }

    private void checkShortCut() {

        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
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

        // 이미 고정된 바로가기를 확인
        List<ShortcutInfo> pinnedShortcuts = getPinnedShortcuts(this);
        for (ShortcutInfo shortcut : pinnedShortcuts) {
            if (shortcut.getId().equals(sShortcutId)) {
                Log.e(TAG, "바로가기 이미 존재: " + sShortcutId);
                return;
            }
        }

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

    private void updateShortcut() {

        String sPackageName = "com.im.daeseong.Shortcut_test";
        String sID = "testID";
        String sLabel = "나의앱 바로가기 (업데이트)";
        String sShortcutId = sPackageName + "_" + sID;

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        // 바로가기 있는지 확인해서 활성화
        List<ShortcutInfo> pinnedShortcuts = getPinnedShortcuts(this);
        for (ShortcutInfo shortcut : pinnedShortcuts) {
            if (shortcut.getId().equals(sShortcutId) && !shortcut.isEnabled()) {
                if (shortcutManager != null) {
                    shortcutManager.enableShortcuts(List.of(sShortcutId));
                }
            }
        }

        // 실행할 인텐트 생성
        Intent intent = new Intent(this, SplashActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("pkg", sPackageName + "업데이트");
        intent.putExtra("userId", sID + "업데이트");

        // 바로가기 정보 생성
        ShortcutInfoCompat updatedShortcutInfo = new ShortcutInfoCompat.Builder(this, sShortcutId)
                .setIntent(intent)
                .setShortLabel(sLabel)
                .setLongLabel(sLabel)
                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground))
                .build();

        // 바로가기 업데이트 요청
        ShortcutManagerCompat.updateShortcuts(this, List.of(updatedShortcutInfo));
    }

    //바로가기 생성 목록
    private List<ShortcutInfo> getPinnedShortcuts(Context context) {
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
        return shortcutManager != null ? shortcutManager.getPinnedShortcuts() : List.of();
    }

    //바로가기 비활성화
    private void disableShortcuts(Context context, String shortcutId) {
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
        if (shortcutManager != null) {
            shortcutManager.disableShortcuts(List.of(shortcutId));
        }
    }
}