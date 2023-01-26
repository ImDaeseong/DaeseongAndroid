package com.im.daeseong.banner_test;

import android.graphics.Bitmap;

public class RowItem {

    private Bitmap bitmap;

    public RowItem(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
}
