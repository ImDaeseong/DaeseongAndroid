package com.im.daeseong.bannerviewpager_test;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.im.daeseong.bannerviewpager_test.Banner_D.BannerView;

public class BannerD_Activity extends AppCompatActivity {

    private static final String TAG = BannerA_Activity.class.getSimpleName();

    private BannerView mBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_d_);

        mBannerView = (com.im.daeseong.bannerviewpager_test.Banner_D.BannerView) findViewById(R.id.mBannerView);
        mBannerView.setImage(BitmapFactory.decodeResource(this.getResources(), R.drawable.number1));
    }
}
