package com.daeseong.ThreadTask_test.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageRunnable implements Runnable{

    private String sUrl;
    private Handler handler = null;

    public DownloadImageRunnable(String sUrl, Handler handler){
        this.sUrl = sUrl;
        this.handler = handler;
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

                Message message = Message.obtain();
                message.obj = "error";
                message.what = 2;
                handler.sendMessage(message);
                return;
            }

            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();

            Message message = Message.obtain();
            message.obj = bitmap;
            message.what = 1;
            handler.sendMessage(message);

        }catch (IOException e){
            e.printStackTrace();

            Message message = Message.obtain();
            message.obj = "error";
            message.what = 2;
            handler.sendMessage(message);

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
