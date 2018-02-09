package com.im.daeseong.http_test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.im.daeseong.http_test.HttpUtil.DownloadJson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ImageTextView1Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DownloadJson.Goods> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text_view1);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);

        try {
            if (IsConnection()) {
                LoadData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void LoadData(){

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {

                String url1 ="https://";
                String sData =  GetUrlData(url1);
                return  sData;
            }

            @Override
            protected void onPostExecute(String s) {
                data = GetList(s);
                setList();
            }
        }.execute();
    }

    public static String GetUrlData(String sUrl)
    {
        StringBuilder sData = new StringBuilder();
        try
        {
            URL url = new URL(sUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection != null)
            {
                connection.setConnectTimeout(10000);
                connection.setUseCaches(false);
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String line = "";
                    while (( line = br.readLine()) != null){
                        sData.append(line + '\n');
                    }
                    br.close();
                    isr.close();
                }
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sData.toString();
    }

    private List<DownloadJson.Goods> GetList(String sDate){

        try {
            Gson gson = new Gson();
            DownloadJson.Goods goods[] = gson.fromJson(sDate, DownloadJson.Goods[].class);
            List<DownloadJson.Goods> result = Arrays.asList(goods);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void setList(){
        ListAdapter adapter = new ListAdapter(data, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
