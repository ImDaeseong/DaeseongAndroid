package com.im.daeseong.bannerviewpager_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.bannerviewpager_test.Banner_A.BannerView;

import java.util.ArrayList;

public class BannerA_Activity extends AppCompatActivity implements BannerView.OnBannerItemClickListener {

    private static final String TAG = BannerA_Activity.class.getSimpleName();

    private BannerView mBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_a_);

        mBannerView = (BannerView) findViewById(R.id.mBannerView);

        ArrayList<BannerView.Banner> banners = new ArrayList<>();
        banners.add(new BannerView.Banner(R.drawable.number1, "title1"));
        banners.add(new BannerView.Banner(R.drawable.number2, "title2"));
        banners.add(new BannerView.Banner(R.drawable.number3, "title3"));
        banners.add(new BannerView.Banner(R.drawable.number4, "title4"));
        mBannerView.setUpData(banners, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBannerView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBannerView.onStop();
    }

    @Override
    public void onBannerClick(int index, BannerView.Banner banner) {

        Log.e(TAG, "index:" + index + " banner:" + banner.title);
    }
}
