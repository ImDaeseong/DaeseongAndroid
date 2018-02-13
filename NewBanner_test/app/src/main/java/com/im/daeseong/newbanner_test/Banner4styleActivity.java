package com.im.daeseong.newbanner_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.newbanner_test.Banner4_style.BannerView;

public class Banner4styleActivity extends AppCompatActivity {

    private static final String TAG = Banner4styleActivity.class.getSimpleName();

    private BannerView bannerView4;

    private int[] imgs = new int[]{R.drawable.number1,R.drawable.number2,R.drawable.number3,R.drawable.number4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner4style);

        bannerView4 = (com.im.daeseong.newbanner_test.Banner4_style.BannerView) findViewById(R.id.mBannerView);
        bannerView4.setBannerData(imgs, new BannerView.ClickListener() {
            @Override
            public void onClink(String url) {
                Log.e(TAG, "url:" + url);
            }
        });
        //bannerView4.setSelectPageIndex(0);
    }
}
