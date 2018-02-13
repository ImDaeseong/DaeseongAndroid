package com.im.daeseong.newbanner_test;

import android.graphics.Bitmap;

public class RowItem {
    public Bitmap bitmap;
    public int ResourceID;

    public RowItem(Bitmap bitmap, int ResouceID){
        this.bitmap = bitmap;
        this.ResourceID = ResouceID;
    }
}
