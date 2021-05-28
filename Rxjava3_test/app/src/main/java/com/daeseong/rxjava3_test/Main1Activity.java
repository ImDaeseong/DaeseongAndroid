package com.daeseong.rxjava3_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private ImageView imageView1, imageView2, imageView3;

    private String sUrl = "https://.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DownLoadfromCallable(sUrl);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DownLoadcreate(sUrl);
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Downloadjust(sUrl);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void DownLoadfromCallable(String sUrl){

        Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {

                Bitmap bm = getBitmapUrl(sUrl);
                return bm;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                //Log.e(TAG, "DownLoadfromCallable onSubscribe");
            }

            @Override
            public void onNext(@NonNull Bitmap bitmap) {

                //Log.e(TAG, "DownLoadfromCallable onNext");

                if (bitmap != null) imageView1.setImageBitmap(bitmap);
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

    private void DownLoadcreate(String sUrl){

        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> emitter) throws Throwable {
                Bitmap bm = getBitmapUrl(sUrl);
                emitter.onNext(bm);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                //Log.e(TAG, "DownLoadcreate onSubscribe");
            }

            @Override
            public void onNext(@NonNull Bitmap bitmap) {

                //Log.e(TAG, "DownLoadcreate onNext");

                if (bitmap != null) imageView2.setImageBitmap(bitmap);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                //Log.e(TAG, "DownLoadcreate onError:" + e.getMessage() );
            }

            @Override
            public void onComplete() {

                //Log.e(TAG, "DownLoadcreate onComplete");
            }
        });
    }

    private void Downloadjust(String sUrl){

        Observable.just(sUrl)
        .map(new Function<String, Bitmap>() {

            @Override
            public Bitmap apply(String s) throws Throwable {
                Bitmap bm = getBitmapUrl(sUrl);
                return bm;
            }

        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                //Log.e(TAG, "Downloadjust onSubscribe");
            }

            @Override
            public void onNext(@NonNull Bitmap bitmap) {

                //Log.e(TAG, "Downloadjust onNext");

                if (bitmap != null) imageView3.setImageBitmap(bitmap);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                //Log.e(TAG, "Downloadjust onError:" + e.getMessage() );
            }

            @Override
            public void onComplete() {

                //Log.e(TAG, "Downloadjust onComplete");
            }
        });
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