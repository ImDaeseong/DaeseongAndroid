package com.im.daeseong.http_test.HttpUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.im.daeseong.http_test.ImageView2Activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadImage1 extends AsyncTask<String, Void, Bitmap> {

    private ImageView2Activity imageView2Activity;

    public DownloadImage1(ImageView2Activity imageView2Activity){
        this.imageView2Activity = imageView2Activity;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try{
            URL url = new URL(params[0]);
            inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView2Activity.ImageViewBitmap(bitmap);
    }
}
