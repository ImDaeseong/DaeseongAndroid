package com.im.daeseong.serviceboot_completed_test;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {

    private static final String TAG = GameInfo.class.getSimpleName();

    public static List<GameItem> getGameApp(Context context){

        List<GameItem> gameList = new ArrayList<>();

        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++){
            PackageInfo info = packages.get(i);

            String appName = info.applicationInfo.loadLabel(context.getPackageManager()).toString();
            String packageName = info.packageName;
            String versionName = info.versionName;
            int versoinCode = info.versionCode;
            Drawable gameIcon = info.applicationInfo.loadIcon(context.getPackageManager());

            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

                //Log.d(TAG, "Installed package (User) :" + packageName);
                GameItem item = new GameItem();
                item.appName = appName;
                item.packageName = packageName;
                item.versionName = versionName;
                item.versoinCode = versoinCode;
                item.gameIcon = gameIcon;
                gameList.add(item);

            } else {
                //Log.e(TAG, "Installed package (System) :" +packageName);
            }
        }
        return gameList;
    }

    public static class GameItem{
        public String appName = "";
        public String packageName = "";
        public String versionName = "";
        public int versoinCode = 0;
        public Drawable gameIcon = null;
    }
}
