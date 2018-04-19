package com.im.daeseong.gallery_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;

public class SearchImage {

    public static String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
    public static String PICTURES = ROOT_DIR + "/Pictures";
    public static String CAMERA = ROOT_DIR + "/DCIM/Camera";

    public static ArrayList<String> getDirList(String sPath){

        ArrayList<String> dirlist = new ArrayList<>();
        File dir = new File(sPath);

        File[] files = dir.listFiles();
        if(files == null) {
            return null;
        }

        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()){
                dirlist.add(files[i].getAbsolutePath());
            }
        }
        return dirlist;
    }

    @Nullable
    public static ArrayList<String> getImageList(String sPath){
        ArrayList<String> dirlist = new ArrayList<>();
        File dir = new File(sPath);

        File[] files = dir.listFiles();
        if(files == null) {
            return null;
        }

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                String sImagePath = files[i].getAbsolutePath();
                String sSearch = getExtName(sImagePath);

                if(sSearch.equals("jpeg") || sSearch.equals("jpg")  || sSearch.equals("png") ){
                    dirlist.add(files[i].getAbsolutePath());
                }
            }
        }
        return dirlist;
    }

    public static String getExtName(String sPath){
        try {
            if (sPath.contains("/")) {
                sPath = sPath.substring(sPath.lastIndexOf("/") + 1);
            }

            if (!sPath.contains(".")) {
                return "";
            }
            return sPath.substring(sPath.lastIndexOf(".") + 1);

        }catch (Exception e){
        }
        return "";
    }

    public static String getFileName(String sPath) {
        try {
            if (!sPath.contains("/")) {
                return sPath;
            }
            return sPath.substring(sPath.lastIndexOf("/") + 1);

        }catch (Exception e){
        }
        return sPath;
    }

    public static Bitmap loadBitmap(String sPath) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 10;
        return BitmapFactory.decodeFile(sPath, option);
    }

}
