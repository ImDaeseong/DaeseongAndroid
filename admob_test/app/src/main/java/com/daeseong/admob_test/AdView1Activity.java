package com.daeseong.admob_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class AdView1Activity extends AppCompatActivity {

    private static final String TAG = AdView1Activity.class.getSimpleName();

    private WebView webView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view1);

        webView = (WebView)findViewById(R.id.webview1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com");

        initAdView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mAdView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAdView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mAdView.destroy();
    }

    private void initAdView(){

        mAdView = findViewById(R.id.adView);
        //mAdView.setAdSize(AdSize.BANNER);
        //표준 배너 크기
        //BANNER 320x50
        //LARGE_BANNER 320x100
        //MEDIUM_RECTANGLE 300x250
        //FULL_BANNER 468x60
        //LEADERBOARD 728x90

        mAdView.setVisibility(View.GONE);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener(){

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                Log.e(TAG, "onAdClicked");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.e(TAG, "onAdClosed");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e(TAG, "onAdFailedToLoad");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.e(TAG, "onAdImpression");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "onAdLoaded");

                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e(TAG, "onAdOpened");
            }
        });

    }
}