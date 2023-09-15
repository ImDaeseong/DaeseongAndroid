package com.daeseong.rxjava3_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private TextView textView1;

    private String sUrl = "https://api.bithumb.com/public/ticker/BTC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView1 = (TextView)findViewById(R.id.textView1);

        DownLoadfromCallable(sUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void DownLoadfromCallable(String sUrl){

        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {

                String sResult = getJsonUrl(sUrl);
                return sResult;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                //Log.e(TAG, "DownLoadfromCallable onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {

                //Log.e(TAG, "onNext:" + s );

                textView1.setText(s);

                try {

                    JSONObject resultObj = new JSONObject(s);
                    String status = resultObj.get("status").toString();
                    if (TextUtils.isEmpty(status)) {
                        status = "";
                    }

                    String data = resultObj.get("data").toString();
                    if( TextUtils.isEmpty( data ) ) {
                        data = "";
                    }

                    if(status.equals("0000")){

                        JSONObject resultData = new JSONObject(data);
                        String opening_price = resultData.get("opening_price").toString();
                        if( TextUtils.isEmpty( opening_price ) ) {
                            opening_price = "";
                        }
                        //Log.e(TAG, opening_price);

                        String closing_price = resultData.get("closing_price").toString();
                        if( TextUtils.isEmpty( closing_price ) ) {
                            closing_price = "";
                        }
                        //Log.e(TAG, closing_price);

                        String min_price = resultData.get("min_price").toString();
                        if( TextUtils.isEmpty( min_price ) ) {
                            min_price = "";
                        }
                        //Log.e(TAG, min_price);

                        String max_price = resultData.get("max_price").toString();
                        if( TextUtils.isEmpty( max_price ) ) {
                            max_price = "";
                        }
                        //Log.e(TAG, max_price);

                    } else {

                        Log.e(TAG, "onNext Error");
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

                //Log.e(TAG, "DownLoadfromCallable onError:" + e.getMessage() );
            }

            @Override
            public void onComplete() {

                //Log.e(TAG, "DownLoadfromCallable onComplete");
            }
        });
    }

    private String getJsonUrl(String sUrl) {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{
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

}