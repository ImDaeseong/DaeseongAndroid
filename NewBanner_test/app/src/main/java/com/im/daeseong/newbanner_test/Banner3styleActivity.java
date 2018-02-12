package com.im.daeseong.newbanner_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.newbanner_test.Banner3_style.BannerView;

import java.util.ArrayList;

public class Banner3styleActivity extends AppCompatActivity {

    private static final String TAG = Banner3styleActivity.class.getSimpleName();

    private BannerView BannerView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner3style);

        BannerView3 = (BannerView)findViewById(R.id.BannerView3);

        ArrayList<BannerView.Banner> banners = new ArrayList<>();
        banners.add(new BannerView.Banner(R.drawable.banner8, "title1"));
        banners.add(new BannerView.Banner(R.drawable.banner8, "title2"));
        banners.add(new BannerView.Banner(R.drawable.banner8, "title3"));
        banners.add(new BannerView.Banner(R.drawable.banner8, "title4"));
        BannerView3.setUpData(banners, new BannerView.BannerItemClickListener() {
            @Override
            public void onClink(String url) {

                Log.e(TAG, "onClink");
            }
        });

    }
}
