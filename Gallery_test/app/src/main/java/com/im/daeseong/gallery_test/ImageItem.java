package com.im.daeseong.gallery_test;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap bitmap;
    private String title;

    public ImageItem(Bitmap bitmap, String title) {
        this.bitmap = bitmap;
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
