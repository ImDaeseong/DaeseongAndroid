package com.daeseong.rxjava_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Rxjava3Activity extends AppCompatActivity {

    private static final String TAG = Rxjava3Activity.class.getSimpleName();

    private ImageView imageView1;

    private Observer<Bitmap> bitmapObservable;

    private String sUrl = "https://.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava3);

        imageView1 = findViewById(R.id.imageView1);

        justImageView();
    }

    private void justImageView() {

        //map 받은 데이터 새로운 형태로 변형

        bitmapObservable = Observable.just(sUrl)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String sUrl) throws Throwable {

                        try {

                            Bitmap bm = getBitmapUrl(sUrl);
                            return bm;

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        return null;
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