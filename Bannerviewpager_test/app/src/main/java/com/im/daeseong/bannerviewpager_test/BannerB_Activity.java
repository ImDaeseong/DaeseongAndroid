package com.im.daeseong.bannerviewpager_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.im.daeseong.bannerviewpager_test.Banner_B.BannerView;


public class BannerB_Activity extends AppCompatActivity {

    private static final String TAG = BannerB_Activity.class.getSimpleName();

    private BannerView bannerView;

    private int[] imgs = new int[]{R.drawable.number1,R.drawable.number2,R.drawable.number3,R.drawable.number4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_b_);

        bannerView = (com.im.daeseong.bannerviewpager_test.Banner_B.BannerView) findViewById(R.id.mBannerView);
        bannerView.setBannerData(imgs);
        bannerView.setSelectPageIndex(3);
    }

}
