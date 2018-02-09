package com.im.daeseong.bannerviewpager_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

import com.im.daeseong.bannerviewpager_test.Banner_G.BannerView;

public class BannerG_Activity extends AppCompatActivity {

    private static final String TAG = BannerG_Activity.class.getSimpleName();

    private BannerView bannerView1, bannerView2, bannerView3, bannerView4, bannerView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_g_);

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.linearLayout5);

        bannerView1 = new BannerView(this);
        bannerView1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        linearLayout1.addView(bannerView1, new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

        bannerView2 = new BannerView(this);
        bannerView2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);//bannerView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        linearLayout2.addView(bannerView2, new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

        bannerView3 = new BannerView(this);
        bannerView3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);//bannerView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        linearLayout3.addView(bannerView3, new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

        bannerView4 = new BannerView(this);
        bannerView4.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);//bannerView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        linearLayout4.addView(bannerView4, new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

        bannerView5 = new BannerView(this);
        bannerView5.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);//bannerView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        linearLayout5.addView(bannerView5, new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }
}
