package com.im.daeseong.glide_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;


public class MainActivity extends AppCompatActivity {

    private ImageView ivImage;
    private String sUlr = "https://www.101apps.co.za/images/headers/101_logo_very_small.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImage = (ImageView)findViewById(R.id.ivImage);

        downloadHttpsImage();

        //download1();
        //download2();
    }

    private void downloadHttpsImage(){
        try {
            Bitmap bitmap = null;
            bitmap = new BannerdisplayImagehttps().execute(sUlr).get();
            if (bitmap != null) {
                ivImage.setImageBitmap(bitmap);
            }else {
                ivImage.setImageResource(R.drawable.banner2);
            }
        }catch (Exception e){
        }
    }

    private void download1(){
        try {
            //trustEveryone();
            Bitmap bitmap = null;
            bitmap = new BannerdisplayImage().execute(sUlr).get();
            if (bitmap != null) {
                ivImage.setImageBitmap(bitmap);
            }else {
                ivImage.setImageResource(R.drawable.banner2);
            }
        }catch (Exception e){
        }
    }

    private void download2(){
        try {
            //trustEveryone();
            Glide.with(this).load(sUlr).into(ivImage);
        }catch (Exception e){
            Glide.with(this).load(R.drawable.banner2).into(ivImage);
        }
    }

    private void trustEveryone() {
        try {

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
        }
    }

}
