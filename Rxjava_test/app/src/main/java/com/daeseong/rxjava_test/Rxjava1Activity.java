package com.daeseong.rxjava_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Rxjava1Activity extends AppCompatActivity {

    private static final String TAG = Rxjava1Activity.class.getSimpleName();

    private ImageView imageView1;

    private Observer<Bitmap> bitmapObservable;

    private String sUrl = "https://.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);

        imageView1 = findViewById(R.id.imageView1);

        fromCallableImageView();
    }

    private void fromCallableImageView() {

        //fromCallable 비동기 처리
        //Runnable 비동기 처리시 return 값을 받을수 없는 구조였고 이 문제를 해결하기 위해 Callable 사용
        //Callable 비동기 처리시 return 값을 받을수 있음

        bitmapObservable = Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {

                Bitmap bm = getBitmapUrl(sUrl);
                return bm;
            }
        })
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull Bitmap bitmap) {

                if (bitmap == null) return;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView1.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.e(TAG, "오류");
            }

            @Override
            public void onComplete() {

                Log.e(TAG, "작업 완료");
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