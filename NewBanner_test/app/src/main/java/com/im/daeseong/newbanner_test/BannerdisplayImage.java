package com.im.daeseong.newbanner_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BannerdisplayImage extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = DownLoadImage(params[0]);
        return bitmap;
    }

    private Bitmap DownLoadImage(String sUrl) {

        String urlImage = sUrl;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Bitmap bitmap = null;
        try{
            URL url = new URL(urlImage);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if(resCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
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
}