package com.daeseong.changedeprecated.Common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class HttpUtil {

    private static final String TAG = HttpUtil.class.getSimpleName();

    public static String GetDataString(String sUrl) {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{

            //SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {

                    if( hostname.equals( session.getPeerHost() ) ) {
                        return true;
                    }
                    return false;
                }
            });

            URL url = new URL(sUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            //httpURLConnection.setConnectTimeout(60);//타임아웃 시간 설정(default:무한대기)
            //httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();

            int resCode = httpURLConnection.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
            }
            httpURLConnection.disconnect();

        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                }
            }
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                }
            }
            if(httpURLConnection != null){
                try{
                    httpURLConnection.disconnect();
                }catch (Exception e){
                }
            }
        }
        return stringBuilder.toString();
    }

    public static Bitmap GetDataBitmap(String sUrl) {

        String urlImage = sUrl;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Bitmap bitmap = null;

        try{

            //SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {

                    if( hostname.equals( session.getPeerHost() ) ) {
                        return true;
                    }
                    return false;
                }
            });

            URL url = new URL(urlImage);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            //httpURLConnection.setConnectTimeout(60);//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            httpURLConnection.disconnect();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                }
            }
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                }
            }
            if(httpURLConnection != null){
                try{
                    httpURLConnection.disconnect();
                }catch (Exception e){
                }
            }
        }
        return bitmap;
    }

}
