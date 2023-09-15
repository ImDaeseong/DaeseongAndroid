package com.im.daeseong.http_test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.im.daeseong.http_test.HttpUtil.SendMessage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendText1Activity extends AppCompatActivity {

    private EditText editText1, editText2, editText3;
    private Button button1;
    private TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text1);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        button1 = (Button)findViewById(R.id.button1);
        textView5 = (TextView)findViewById(R.id.textView5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sText1 = editText1.getText().toString();
                String sText2 = editText2.getText().toString();
                String sText3 = editText3.getText().toString();

                String postParams = "USERNO="+sText1+"&NAME="+sText2+"&PHONE=" + sText3;
                String url1 = "https://";
                SendMessage sendMessage = new SendMessage(SendText1Activity.this, textView5);
                sendMessage.execute(url1, postParams);


                //첫번째 방법
                //SendTask sendTask = new SendTask();
                //sendTask.execute(postParams);
            }
        });
    }

    public class SendTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String url1 = "https://";
            String sPostData = params[0];

            HttpURLConnection httpURLConnection = null;
            DataOutputStream dataOutputStream = null;
            BufferedReader bufferedReader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try{
                URL url = new URL(url1);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                //httpURLConnection.setConnectTimeout(10000);
                //httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(sPostData);
                dataOutputStream.flush();
                dataOutputStream.close();

                int resCode = httpURLConnection.getResponseCode();
                if(resCode == 200){
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                }else {
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                }

                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                httpURLConnection.disconnect();
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

            Log.e("daeseong", s);
        }
    }


    public boolean IsConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return false;
        }

        if(!networkInfo.isConnected()){
            return  false;
        }

        if(!networkInfo.isAvailable()){
            return false;
        }
        return true;
    }



}
