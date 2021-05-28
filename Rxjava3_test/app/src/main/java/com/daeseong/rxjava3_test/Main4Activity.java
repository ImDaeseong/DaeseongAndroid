package com.daeseong.rxjava3_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private String sUrl = "https://api.bithumb.com/public/ticker/BTC";
    private String sImgUrl = "https://.png";

    private Button button1, button2, button3, button4;

    private TextView textView1, textView2;
    private ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getJson1(sUrl).subscribe(result -> {

                    //Log.e(TAG, "result:" + result );
                    textView1.setText(result);
                });
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getJson2(sUrl).subscribe(result -> {

                    //Log.e(TAG, "result:" + result );
                    textView2.setText(result);
                });
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getBitmap1(sImgUrl).subscribe(result -> {

                    if (result != null) imageView1.setImageBitmap(result);
                });
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getBitmap2(sImgUrl).subscribe(result -> {

                    if (result != null) imageView2.setImageBitmap(result);
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private @NonNull Observable<String> getJson1(String sUrl){

        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String sResult = getJsonUrl(sUrl);
                return sResult;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private @NonNull Observable<String> getJson2(String sUrl){

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                String sResult = getJsonUrl(sUrl);
                return sResult;
            }
        };

        Observable<String> result = Observable.fromCallable(callable).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return result;
    }

    private @NonNull Observable<Bitmap> getBitmap1(String sUrl){

        return Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                Bitmap bm = getBitmapUrl(sUrl);
                if(bm == null){
                    Log.e(TAG, "Bitmap null");
                }
                return bm;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private @NonNull Observable<Bitmap> getBitmap2(String sUrl){

        Callable<Bitmap> callable = new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                Bitmap bm = getBitmapUrl(sUrl);
                if(bm == null){
                    Log.e(TAG, "Bitmap null");
                }
                return bm;
            }
        };

        Observable<Bitmap> result = Observable.fromCallable(callable).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return result;
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

    private Bitmap getBitmapUrl(String urlImage) {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Bitmap bitmap = null;
        try{
            URL url = new URL(urlImage);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            //httpURLConnection.setConnectTimeout(60);//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if(resCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();
        }catch (IOException e){
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
        return bitmap;
    }
}