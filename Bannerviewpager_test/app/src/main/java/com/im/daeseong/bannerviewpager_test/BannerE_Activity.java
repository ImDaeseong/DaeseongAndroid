package com.im.daeseong.bannerviewpager_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.im.daeseong.bannerviewpager_test.Banner_C.BannerAdapter;
import com.im.daeseong.bannerviewpager_test.Banner_C.BannerView;

public class BannerE_Activity extends AppCompatActivity {

    private static final String TAG = BannerC_Activity.class.getSimpleName();

    private BannerView bannerView;
    private BannerAdapter pagerAdapter;
    private RelativeLayout rLayout;
    private int[] imgs = new int[]{R.drawable.number1,R.drawable.number2,R.drawable.number3,R.drawable.number4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_e_);

        rLayout = (RelativeLayout) findViewById(R.id.mBannerView);
        pagerAdapter = new BannerAdapter(getApplicationContext(), imgs);
        bannerView = new BannerView(getApplicationContext(), imgs, pagerAdapter,R.layout.widget_banner_e_view);
        rLayout.addView(bannerView.getBannerView());
    }
}
