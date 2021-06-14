package com.daeseong.onestore_test;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class DownloadJson extends AsyncTask<String, Void, String> {

    private JsonListener jsonListener;

    public DownloadJson() {
    }

    @Override
    protected String doInBackground(String... params) {

        String urlText = params[0];
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

            URL url = new URL(urlText);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
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
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String sResult) {
        if(jsonListener != null){
            jsonListener.onResult(sResult);
        }
    }

    public void setJsonListener(JsonListener jsonListener){
        this.jsonListener = jsonListener;
    }

    public interface JsonListener{
        void onResult(String sResult);
    }
}