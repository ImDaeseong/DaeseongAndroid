package com.im.daeseong.newbanner_test;

import android.graphics.Bitmap;

/**
 * Created by Daeseong on 2018-02-12.
 */

public class RowItem {
    public Bitmap bitmap;
    public int ResourceID;

    public RowItem(Bitmap bitmap, int ResouceID){
        this.bitmap = bitmap;
        this.ResourceID = ResouceID;
    }
}
