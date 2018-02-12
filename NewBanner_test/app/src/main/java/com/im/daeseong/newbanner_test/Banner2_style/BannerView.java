package com.im.daeseong.newbanner_test.Banner2_style;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.im.daeseong.newbanner_test.MainActivity;

/**
 * Created by Daeseong on 2018-02-09.
 */

public class BannerView  extends RelativeLayout implements View.OnTouchListener {

    private static final String TAG = BannerView.class.getSimpleName();

    private WebView banner2style;
    private float mDensity;
    private Activity activity;
    private String url;
    private int WIDTH = 0;//300;
    private int HEIGHT = 0;//50;

    public BannerView(Activity activity, String url, int WIDTH, int HEIGHT) {
        this(activity, url, WIDTH,  HEIGHT, null, 0);
    }

    public BannerView(Activity activity, String url, int WIDTH, int HEIGHT, AttributeSet attrs) {
        this(activity, url, WIDTH,  HEIGHT, attrs, 0);
    }

    public BannerView(Activity activity, String url, int WIDTH, int HEIGHT, AttributeSet attrs, int defStyle) {
        super(activity, attrs, defStyle);
        this.activity = activity;
        this.url = url;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        banner2style = null;
        init(this.activity.getApplicationContext());
        load();
    }

    private void init(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        setOnTouchListener(this);
    }

    private void load() {
        banner2style = new WebView(getContext());

        if( (this.WIDTH > 0) && (this.HEIGHT > 0) ){
            addView(banner2style, new android.widget.RelativeLayout.LayoutParams((int)(this.WIDTH * mDensity), (int)(this.HEIGHT * mDensity)));
        }else {
            addView(banner2style, new android.widget.RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ));
        }

        banner2style.setOnTouchListener(this);
        banner2style.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e(TAG, "shouldOverrideUrlLoading");
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e(TAG, "onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e(TAG, "onPageFinished");
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e(TAG, "onPageFinished");
            }
        });

        WebSettings webSettings = banner2style.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        String databasePath = banner2style.getContext().getDir("wall", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabasePath(databasePath);

        banner2style.loadUrl(url);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(this.activity, MainActivity.class);
                this.activity.startActivity(intent);
                this.activity.finish();
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            default:
                break;
        }
        return true;
    }
}
