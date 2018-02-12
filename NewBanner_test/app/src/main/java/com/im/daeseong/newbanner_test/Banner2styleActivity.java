package com.im.daeseong.newbanner_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.im.daeseong.newbanner_test.Banner2_style.BannerView;

public class Banner2styleActivity extends AppCompatActivity {

    private static final String TAG = Banner2styleActivity.class.getSimpleName();

    private BannerView BannerView1, BannerView2, BannerView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2style);

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        BannerView1 = new BannerView(this, "https://m.naver.com/", 0, 0);
        linearLayout1.addView(BannerView1, new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        BannerView2 = new BannerView(this, "https://m.naver.com/", 300, 50);
        BannerView2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        linearLayout2.addView(BannerView2, new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        BannerView3 = new BannerView(this, "https://m.naver.com/", 0, 0);
        linearLayout3.addView(BannerView3, new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));

    }
}
