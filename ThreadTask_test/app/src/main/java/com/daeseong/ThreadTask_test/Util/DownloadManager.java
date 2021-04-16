package com.daeseong.ThreadTask_test.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadManager {

    private static DownloadManager instance;
    public static DownloadManager getInstance() {
        if (instance == null) {
            instance = new DownloadManager();
        }
        return instance;
    }

    private Handler handler;
    private DownloadManager() {
        handler = new Handler(Looper.getMainLooper());
    }

    public interface onCompleteListen{
        public void onCompleted(Bitmap bitmap);
        public void onError(String sMessage);
    }

    public void getImage(String sUrl, onCompleteListen listen) {
        new Thread(new DownloadRunnable(sUrl, listen)).start();
    }

    private class DownloadRunnable implements Runnable {

        private String sUrl;
        private DownloadManager.onCompleteListen listen;

        public DownloadRunnable(String sUrl, DownloadManager.onCompleteListen listen)
        {
            this.sUrl = sUrl;
            this.listen = listen;
        }

        @Override
        public void run() {

            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;

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
                                listen.onError( "error");
                            }
                        }
                    });
                    return;
                }

                inputStream = httpURLConnection.getInputStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                httpURLConnection.disconnect();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listen != null) {
                            listen.onCompleted(bitmap);
                        }
                    }
                });

            }catch (IOException e){
                e.printStackTrace();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listen != null) {
                            listen.onError("error");
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

    }
}


