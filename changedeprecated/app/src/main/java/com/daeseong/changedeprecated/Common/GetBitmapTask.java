package com.daeseong.changedeprecated.Common;

import android.graphics.Bitmap;

public class GetBitmapTask extends ThreadTask<String, Bitmap> {

    private Bitmap bm = null;

    @Override
    protected Bitmap doInBackground(String sUrl) {

        try {
            bm = HttpUtil.GetDataBitmap(sUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bm;
    }
}