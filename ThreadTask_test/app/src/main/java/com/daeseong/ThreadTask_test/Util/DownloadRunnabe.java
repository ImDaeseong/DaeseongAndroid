package com.daeseong.ThreadTask_test.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadRunnabe implements Runnable {

    private String sUrl;
    private Bitmap bitmap;
    private Handler handler = null;

    private onDownloadRunnabeListen listen;

    public DownloadRunnabe(String sUrl){
        this.sUrl = sUrl;
    }

    public void start(Handler handler, onDownloadRunnabeListen listener) {
        this.handler = handler;
        this.listen = listener;
        new Thread(this).start();
    }

    public Bitmap getBitmap(){
        return this.bitmap;
    }

    @Override
    public void run() {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Bitmap bitmap = null;

        try{
            URL url = new URL(this.sUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if(resCode != HttpURLConnection.HTTP_OK) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listen != null) {
                            listen.onError(DownloadRunnabe.this, "error");
                        }
                    }
                });
                return;
            }

            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();

            this.bitmap = bitmap;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (listen != null) {
                        listen.onCompleted(DownloadRunnabe.this);
                    }
                }
            });

        }catch (IOException e){
            e.printStackTrace();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (listen != null) {
                        listen.onError(DownloadRunnabe.this, "error");
                    }
                }
            });

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
    }

    public interface onDownloadRunnabeListen{
        public void onCompleted(DownloadRunnabe downloadRunnabe);
        public void onError(DownloadRunnabe downloadRunnabe, String sMessage);
    }
}
