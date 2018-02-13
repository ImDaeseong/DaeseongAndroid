package com.im.daeseong.newbanner_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.newbanner_test.Banner5_style.BannerView;

public class Banner5styleActivity extends AppCompatActivity {

    private static final String TAG = Banner5styleActivity.class.getSimpleName();

    private BannerView bannerView5;

    private int[] imgs = new int[]{R.drawable.netmarbleadv02,R.drawable.netmarbleadv03,R.drawable.netmarbleadv04,R.drawable.banner8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner5style);

        bannerView5 = (BannerView) findViewById(R.id.mBannerView);
        bannerView5.setBannerData(imgs, new BannerView.ClickListener() {
            @Override
            public void onClink(String url) {
                Log.e(TAG, "url:" + url);
            }
        });
    }
}
