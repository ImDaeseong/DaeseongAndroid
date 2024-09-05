package com.im.daeseong.newbanner_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.im.daeseong.newbanner_test.Banner3_style.BannerView;

public class Banner3styleActivity extends AppCompatActivity {

    private static final String TAG = Banner3styleActivity.class.getSimpleName();

    private BannerView BannerView3;

    private int[] imgs = new int[]{R.drawable.number1,R.drawable.number2,R.drawable.number3,R.drawable.number4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner3style);

        BannerView3 = (com.im.daeseong.newbanner_test.Banner3_style.BannerView) findViewById(R.id.mBannerView);
        BannerView3.setBannerData(imgs);

    }
}
