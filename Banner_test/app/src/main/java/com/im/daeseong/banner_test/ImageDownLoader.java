package com.im.daeseong.banner_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.net.URL;

public class ImageDownLoader extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = ImageDownLoader.class.getName();

    private ImageView imageView;
    public ImageDownLoader(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(urls[0]).openStream());
        } catch (Exception e) {
            e.printStackTrace();
            cancel(true);
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        try{
            if(bitmap != null){
                this.imageView.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCancelled() {
        Log.e(TAG, "onCancelled");
    }

}