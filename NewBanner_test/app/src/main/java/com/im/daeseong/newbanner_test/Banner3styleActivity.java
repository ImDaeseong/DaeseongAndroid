package com.im.daeseong.newbanner_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.newbanner_test.Banner3_style.BannerView;

import java.util.ArrayList;

public class Banner3styleActivity extends AppCompatActivity {

    private static final String TAG = Banner3styleActivity.class.getSimpleName();

    private BannerView BannerView3;

    private int[] imgs = new int[]{R.drawable.banner8,R.drawable.banner8,R.drawable.banner8,R.drawable.banner8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner3style);

        BannerView3 = (com.im.daeseong.newbanner_test.Banner3_style.BannerView) findViewById(R.id.mBannerView);
        BannerView3.setBannerData(imgs, new com.im.daeseong.newbanner_test.Banner3_style.BannerView.ClickListener() {
            @Override
            public void onClink(String url) {
                Log.e(TAG, "url:" + url);
            }
        });

    }
}
