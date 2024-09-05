package com.im.daeseong.http_test.HttpUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class SendMessage extends AsyncTask<String, Void, String> {

    private final Context context;
    private final ConnectivityManager connectivityManager;
    private TextView textView;

    public SendMessage(Context context, TextView textView){
        this.context = context;
        this.textView = textView;
        connectivityManager = (ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    protected String doInBackground(String... params) {

        String urlText = params[0];
        String urlParameters = params[1];

        HttpURLConnection httpURLConnection = null;
        DataOutputStream dataOutputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{

            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            for(NetworkInfo info : networkInfo){

                if(info.getState() == NetworkInfo.State.CONNECTED){

                    byte[] postData = urlParameters.getBytes("UTF-8");
                    int postDataLength = postData.length;

                    URL url = new URL(urlText);
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setInstanceFollowRedirects(false);
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpURLConnection.setRequestProperty("charset", "utf-8");
                    httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();

                    dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postData);
                    dataOutputStream.flush();
                    dataOutputStream.close();

                    int resCode = httpURLConnection.getResponseCode();
                    if(resCode == 200){
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    }else {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    }

                    String line = "";
                    while ((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line);
                    }
                    httpURLConnection.disconnect();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(dataOutputStream != null){
                try{
                    dataOutputStream.close();
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

        try{
            if(s != null){

                Gson gson = new Gson();
                ResultRes result = gson.fromJson(s, ResultRes.class);

                String code = "CODE:" + result.CODE + "\n";
                String msg = "MESSAGE:" + result.MESSAGE + "\n";
                String name = "NAME:" + result.DATA.NAME + "\n";
                String userno = "USERNO:" + result.DATA.USERNO + "\n";
                String phone = "PHONE:" + result.DATA.PHONE;
                String sMsg =  code + msg + name + userno + phone;

                this.textView.setText(sMsg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ResultRes{
        public  String CODE;
        public String MESSAGE;
        public itemData DATA;
    }
    public class itemData {
        public String NAME;
        public String USERNO;
        public String PHONE;
    }

}
