package com.im.daeseong.Shortcut_test;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

public class ShortCutUtil {

    private static final String TAG = ShortCutUtil.class.getSimpleName();

    private final Context context;

    public ShortCutUtil(Context context) {
        this.context = context;
    }

    public void checkShortCut() {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
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
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("pkg", sPackageName);
        intent.putExtra("userId", sID);

        // 바로가기 정보 생성
        ShortcutInfoCompat shortcutInfo = new ShortcutInfoCompat.Builder(context, sShortcutId)
                .setIntent(intent)
                .setShortLabel(sLabel)
                .setLongLabel(sLabel)
                .setIcon(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground))
                .build();

        // 바로가기 생성 요청
        ShortcutManagerCompat.requestPinShortcut(context, shortcutInfo, null);
    }
}
