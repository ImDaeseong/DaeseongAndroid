package com.im.daeseong.gameinfo_test;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class GameInfo {

    private static final String TAG = GameInfo.class.getSimpleName();

    /*
    private final static String[] GAME_PACKAGE_NAMES = {
            "ds.id.Omong",
            "ds.id.Bahasa",
            "ds.id.BahasaKorea"
    };
    */

    private final static String[] GAME_PACKAGE_NAMES = {
            "com.nexon.overhit",//오버히트
            "com.netmarble.tera"//테라M
    };

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

                //getApkPath(context, packageName);

                if(isGamePackageName(packageName)){
                    GameItem item = new GameItem();
                    item.appName = appName;
                    item.packageName = packageName;
                    item.versionName = versionName;
                    item.versoinCode = versoinCode;
                    item.gameIcon = gameIcon;
                    gameList.add(item);
                }

            } else {
                //Log.e(TAG, "Installed package (System) :" +packageName);
            }
        }
        return gameList;
    }

    public static String getApkPath(Context context, String packageName){
        String sSourceDir = null;
        try{
            //Log.e(TAG, "publicSourceDir:" + context.getPackageManager().getApplicationInfo(packageName, 0).publicSourceDir);
            sSourceDir = context.getPackageManager().getApplicationInfo(packageName, 0).sourceDir;
            //Log.e(TAG, "sSourceDir2:" + sSourceDir);
        }catch (Exception e){
        }
        return sSourceDir;
    }

    private static boolean isGamePackageName(final String packageName){

        final int size = GAME_PACKAGE_NAMES.length;
        for (int i = 0; i < size; i++) {
            if (packageName.startsWith(GAME_PACKAGE_NAMES[i])) {
                return true;
            }
        }
        return false;
    }

    public static class GameItem{
        public String appName = "";
        public String packageName = "";
        public String versionName = "";
        public int versoinCode = 0;
        public Drawable gameIcon = null;
    }
}
