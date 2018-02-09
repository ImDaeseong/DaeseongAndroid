package com.im.daeseong.http_test.HttpUtil;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.im.daeseong.http_test.ImageTextView2Activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImage2 extends AsyncTask<Void, Void, Bitmap> {

    String url1 = "https://jpg";
    private ImageTextView2Activity imageTextView2Activity;
    private ProgressDialog progressDialog;

    public DownloadImage2(ImageTextView2Activity imageTextView2Activity){
        this.imageTextView2Activity = imageTextView2Activity;
        progressDialog = ProgressDialog.show(imageTextView2Activity, "접속중...", "이미지 다운로드중...", true);
    }

    @Override
    protected Bitmap doInBackground(Void... params) {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try{
            URL url = new URL(url1);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(httpURLConnection != null){
                try{
                    httpURLConnection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        progressDialog.dismiss();;
        imageTextView2Activity.ImageViewBitmap(bitmap);
    }
}
