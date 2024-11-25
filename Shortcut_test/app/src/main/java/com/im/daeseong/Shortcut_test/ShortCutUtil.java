package com.im.daeseong.Shortcut_test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.util.Log;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import java.util.Collections;
import java.util.List;

public class ShortCutUtil {

    private static final String TAG = ShortCutUtil.class.getSimpleName();

    public static void checkShortCut(Context context, String sPackageName, String sLabel, String sID, String sData) {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
            Log.e(TAG, "홈 화면에 바로가기 지원");
            createShortCut(context, sPackageName, sLabel, sID, sData);
        } else {
            Log.e(TAG, "홈 화면에 바로가기 미지원");
        }
    }

    private static void createShortCut(Context context, String sPackageName, String sLabel, String sID, String sData) {

        String sShortcutId = sPackageName + "_" + sID;

        boolean bUpdate = false;
        ShortcutInfo existingShortcut = null;

        // 이미 고정된 바로가기를 확인
        List<ShortcutInfo> pinnedShortcuts = getPinnedShortcuts(context);
        for (ShortcutInfo shortcut : pinnedShortcuts) {
            if (shortcut.getId().equals(sShortcutId)) {
                bUpdate = true;
                existingShortcut = shortcut;
                break;
            }
        }

        // 이미 존재하는지 여부 체크
        if (bUpdate) {
            Log.e(TAG, "바로가기 이미 존재: " + sShortcutId);

            ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
            if (!existingShortcut.isEnabled()) {
                try {
                    Log.e(TAG, "바로가기가 비활성화된 상태라면 활성화:" + sShortcutId);
                    List<String> shortcutIds = Collections.singletonList(sShortcutId);
                    shortcutManager.enableShortcuts(shortcutIds);
                } catch (Exception ex) {
                }
            }
        }

        // 실행할 인텐트 생성
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("pkg", sPackageName);
        intent.putExtra("userId", sID);
        intent.putExtra("userData", sData);

        // 바로가기 정보 생성
        ShortcutInfoCompat shortcutInfo = new ShortcutInfoCompat.Builder(context, sShortcutId)
                .setIntent(intent)
                .setShortLabel(sLabel)
                .setLongLabel(sLabel)
                .setIcon(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground))
                .build();


        if (bUpdate) {
            Log.e(TAG, "바로가기 업데이트: " + sShortcutId);
            ShortcutManagerCompat.updateShortcuts(context, java.util.Collections.singletonList(shortcutInfo));
        } else {
            Log.e(TAG, "바로가기 생성: " + sShortcutId);
            ShortcutManagerCompat.requestPinShortcut(context, shortcutInfo, null);
        }
    }

    // 바로가기 생성 목록
    private static List<ShortcutInfo> getPinnedShortcuts(Context context) {
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
        return shortcutManager != null ? shortcutManager.getPinnedShortcuts() : List.of();
    }

    //바로가기 존재 여부
    public static boolean isPinnedShortcuts(Context context, String sID) {

        String shortcutId = context.getPackageName() + "_" + sID;

        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

        List<ShortcutInfo> pinnedShortcuts = (shortcutManager != null) ? shortcutManager.getPinnedShortcuts() : List.of();
        for (ShortcutInfo shortcut : pinnedShortcuts) {
            if (shortcut.getId().equals(shortcutId)) {
                return true;
            }
        }

        return false;
    }

    //바로가기 비활성화 -보안상의 이유로 앱에서 바탕화면의 바로가기를 직접 삭제하는 것은 제한
    public static void removePinnedShortcut(Context context, String sID) {

        String shortcutId = context.getPackageName() + "_" + sID;

        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

        if (isPinnedShortcuts(context, sID)) {

            List<String> shortcutsToRemove = Collections.singletonList(shortcutId);

            try {

                // 바로가기 비활성화
                if (shortcutManager != null) {
                    shortcutManager.removeDynamicShortcuts(shortcutsToRemove);
                    shortcutManager.disableShortcuts(shortcutsToRemove, null);
                }

            } catch (Exception e) {
            }
        }
    }

}
