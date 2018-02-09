package com.im.daeseong.http_test.HttpUtil;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.im.daeseong.http_test.ImageTextView2Activity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadJson1 extends AsyncTask<Void, Void, String> {

    String url1 ="https://";
    private ImageTextView2Activity imageTextView2Activity;
    private ProgressDialog progressDialog;

    public DownloadJson1(ImageTextView2Activity imageTextView2Activity){
        this.imageTextView2Activity = imageTextView2Activity;
        progressDialog = ProgressDialog.show(imageTextView2Activity, "접속중...", "이미지 다운로드중...", true);
    }

    @Override
    protected String doInBackground(Void... params) {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            URL url = new URL(url1);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        imageTextView2Activity.LoadjsonData(s);
    }
}
