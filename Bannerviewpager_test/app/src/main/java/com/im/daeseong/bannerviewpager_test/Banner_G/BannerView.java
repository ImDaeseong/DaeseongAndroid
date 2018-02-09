package com.im.daeseong.bannerviewpager_test.Banner_G;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.RelativeLayout;
import android.view.WindowManager;
import android.view.View;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.util.Log;

import com.im.daeseong.bannerviewpager_test.BannerGsub_Activity;

public class BannerView extends RelativeLayout implements View.OnClickListener, View.OnTouchListener
{
    private WebView mWebView;
    private float mDensity;
    private static final String TAG = BannerView.class.getSimpleName();
    private Activity mActivity;

    public final class BannerSize {
        public static final float WIDTH = 320;
        public static final float HEIGHT = 50;
    }

    public BannerView(Activity activity) {
        this(activity, null, 0);
    }

    public BannerView(Activity activity, AttributeSet attrs) {
        this(activity, attrs, 0);
    }

    public BannerView(Activity activity, AttributeSet attrs, int defStyle) {
        super(activity, attrs, defStyle);
        mActivity = activity;
        Log.d(TAG, "banner start");
        mWebView = null;
        init(mActivity.getApplicationContext());
        load();
    }

    private void init(Context context) {

        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;

        setOnClickListener(this);
        setOnTouchListener(this);
    }

    private void load() {
        mWebView = new WebView(getContext());
        addView(mWebView, new android.widget.RelativeLayout.LayoutParams((int)(BannerSize.WIDTH * mDensity), (int)(BannerSize.HEIGHT * mDensity)));
        mWebView.setOnClickListener(this);
        mWebView.setOnTouchListener(this);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        String databasePath = mWebView.getContext().getDir("wall", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabasePath(databasePath);

        mWebView.loadUrl("http://m.naver.com");

        Log.d(TAG, "load banner");

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "show wall");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "show wall");
                BannerGsub_Activity.show(mActivity);
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            default:
                break;
        }
        return true;
    }
}