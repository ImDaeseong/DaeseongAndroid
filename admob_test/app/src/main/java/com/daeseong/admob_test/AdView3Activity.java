package com.daeseong.admob_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class AdView3Activity extends AppCompatActivity {

    private static final String TAG = AdView3Activity.class.getSimpleName();

    private WebView webView;
    private RewardedAd rewardedAd;

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view3);

        webView = (WebView)findViewById(R.id.webview1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com");

        initrewardedAd();

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showrewardedAd();
            }
        });
    }

    private void initrewardedAd(){

        rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/8691691433");
        AdRequest adRequest = new AdRequest.Builder().build();
        rewardedAd.loadAd(adRequest, new RewardedAdLoadCallback(){

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
                Log.e(TAG, "onRewardedAdLoaded");
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                super.onRewardedAdFailedToLoad(loadAdError);
                Log.e(TAG, "onRewardedAdFailedToLoad");
            }
        });
    }

    private void showrewardedAd() {

        if ( rewardedAd.isLoaded() ) {

            RewardedAdCallback rewardedAdCallback = new RewardedAdCallback() {

                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.e(TAG, "onUserEarnedReward");

                    //보상타입
                    String sType = rewardItem.getType();

                    //보상수량
                    int nCount = rewardItem.getAmount();

                    Log.e(TAG, "보상타입:" + sType + " 보상수량:" +  String.valueOf(nCount));
                }

                @Override
                public void onRewardedAdOpened() {
                    super.onRewardedAdOpened();
                    Log.e(TAG, "onRewardedAdOpened");
                }

                @Override
                public void onRewardedAdClosed() {
                    super.onRewardedAdClosed();
                    Log.e(TAG, "onRewardedAdClosed");
                }

                @Override
                public void onRewardedAdFailedToShow(AdError adError) {
                    super.onRewardedAdFailedToShow(adError);
                    Log.e(TAG, "onRewardedAdFailedToShow");
                }
            };
            rewardedAd.show(this, rewardedAdCallback);
        }

    }
}