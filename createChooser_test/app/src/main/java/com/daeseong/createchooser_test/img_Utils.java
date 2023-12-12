package com.daeseong.createchooser_test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.FileProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class img_Utils {

    private static final String TAG = img_Utils.class.getSimpleName();

    public static Observable<Bitmap> GetObservableBitmap(String sUrl) {

        return Observable.fromCallable(new Callable<Bitmap>() {
                    @Override
                    public Bitmap call() throws Exception {

                        Bitmap bm = null;
                        try {
                            bm = GetHttpBitmap(sUrl);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return bm;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Uri GetAsset(Context context, String assetFileName) {

        try {
            File file = new File(context.getCacheDir(), "images/" + assetFileName);
            if (!file.exists()) {
                InputStream inputStream = context.getAssets().open(assetFileName);
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int read;
                while ((read = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, read);
                }
                inputStream.close();
                outputStream.close();
            }
            return FileProvider.getUriForFile(context, "com.daeseong.createchooser_test.fileprovider", file);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
        return null;
    }

    public static Uri GetUri(Context context, Bitmap bitmap) {

        ContentResolver resolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "image_name");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/png");

        Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Uri imageUri = resolver.insert(contentUri, contentValues);

        try {
            if (imageUri != null) {
                try (OutputStream outputStream = resolver.openOutputStream(imageUri)) {
                    if (outputStream != null) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                        return imageUri;
                    }
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
        return null;
    }

    public static Bitmap GetHttpBitmap(String sUrl) {

        String urlImage = sUrl;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Bitmap bitmap = null;

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

            URL url = new URL(urlImage);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            //httpURLConnection.setConnectTimeout(60);//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            httpURLConnection.disconnect();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                }
            }
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                }
            }
            if(httpURLConnection != null){
                try{
                    httpURLConnection.disconnect();
                }catch (Exception e){
                }
            }
        }
        return bitmap;
    }

}
