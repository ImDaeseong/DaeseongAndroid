package com.daeseong.ThreadTask_test.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Handler;

public class DownloadText implements Runnable{

    private String sUrl;
    private String sText;
    private Handler handler = null;

    private onDownloadTextListen listen;

    public DownloadText(String sUrl, Handler handler){
        this.sUrl = sUrl;
        this.handler = handler;
    }

    public void setonDownloadTextListen(onDownloadTextListen listen){
        this.listen = listen;
    }

    public void start(){
        new Thread(this).start();
    }

    public String getText(){
        return this.sText;
    }

    @Override
    public void run() {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{

            URL url = new URL(this.sUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect();

            int resCode = httpURLConnection.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
            }
            httpURLConnection.disconnect();

            this.sText = stringBuilder.toString();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (listen != null) {
                        listen.onCompleted(DownloadText.this);
                    }
                }
            });

        }catch (IOException e){
            e.printStackTrace();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (listen != null) {
                        listen.onError(DownloadText.this, "error");
                    }
                }
            });

        }finally{
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

    public interface onDownloadTextListen{
        public void onCompleted(DownloadText downloadText);
        public void onError(DownloadText downloadText, String sMessage);
    }
}
