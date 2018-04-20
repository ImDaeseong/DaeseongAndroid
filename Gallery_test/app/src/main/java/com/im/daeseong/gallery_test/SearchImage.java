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

    private static ArrayList<String> allDir;
    private static ArrayList<String> allPicture;

    public static ArrayList<String> getAllPicture(){

        allPicture = new ArrayList<>();
        allDir = new ArrayList<>();
        if (getDirList(SearchImage.PICTURES) != null) {
            allDir = getDirList(SearchImage.PICTURES);
        }
        allDir.add(SearchImage.CAMERA);

        for (String dir : allDir) {
            getImageList(dir);
        }
        return allPicture;
    }

    private static ArrayList<String> getDirList(String sPath){

        File dir = new File(sPath);
        File[] fileList = dir.listFiles();
        if(fileList == null) return null;

        for (File current : fileList){
            if(current.isDirectory()){
                allDir.add(current.getAbsolutePath());
            }
        }
        return allDir;
    }

    @Nullable
    private static void getImageList(String sPath){

        File dir = new File(sPath);
        File[] fileList = dir.listFiles();

        if(fileList != null) {

            for (File current : fileList) {
                if (current.isFile()) {

                    String sImagePath = current.getAbsolutePath();
                    String sSearch = getExtName(sImagePath);

                    if (sSearch.equals("jpeg") || sSearch.equals("jpg") || sSearch.equals("png")) {
                        allPicture.add(sImagePath);
                    }
                }
            }
        }
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
