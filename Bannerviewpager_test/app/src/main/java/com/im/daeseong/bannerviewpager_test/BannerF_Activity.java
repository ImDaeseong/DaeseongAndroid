package com.im.daeseong.bannerviewpager_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.bannerviewpager_test.Banner_F.BannerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BannerF_Activity extends AppCompatActivity {

    private static final String TAG = BannerF_Activity.class.getSimpleName();

    BannerView bannerView;

    private int[] imgs = new int[]{R.drawable.number1,R.drawable.number2,R.drawable.number3,R.drawable.number4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_f_);

        bannerView = (BannerView)findViewById(R.id.bannerView);
        bannerView.setBannerView(imgs, new BannerView.ClickListener() {
            @Override
            public void onClink(String url) {
                Log.e(TAG, "url:" + url);
            }
        });
    }

}
