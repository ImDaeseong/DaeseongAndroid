package com.im.daeseong.webview_test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.appcompat.app.AlertDialog;//import android.support.v7.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebView2Activity extends AppCompatActivity {

    private static final String TAG = WebView2Activity.class.getSimpleName();

    public String sTitle;
    public Context context;
    private WebView webView;
    private ProgressBar progressBar;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view2);
        context = getApplicationContext();

        progressBar = (ProgressBar)findViewById(R.id.progressBar1);

        webView = (WebView)findViewById(R.id.webview1);
        webView.getSettings().setJavaScriptEnabled(true);//웹뷰에서 자바스크립트 실행 가능

        //webView.setBackgroundColor(0);
        //webView.setHorizontalScrollBarEnabled(false);
        //webView.setVerticalScrollBarEnabled(false);

        webView.addJavascriptInterface(new webJavaScriptInterface(this), "Android");

        webView.setWebViewClient(new CustomWebViewClient() );

        //웹뷰에서 자바스크립트 alert과 confirm 이 동작하게 처리
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (progressBar.getVisibility() == View.GONE)
                        progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            //alert 처리
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("알림1")
                        .setMessage(message)
                        .setPositiveButton("네",//android.R.string.ok,
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            }

            //confirm 처리
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("알림1")
                        .setMessage(message)
                        .setPositiveButton("네",
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.confirm();
                                    }
                                })
                        .setNegativeButton("아니오",
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.cancel();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                Log.e(TAG, "title:" + title);
            }
        });

        //네트워크 연결 여부
        if(isNetworkAvailable(this)){
            webView.loadUrl("file:///android_asset/test2.html");
        }else {
            webView.loadUrl("about:blank");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //웹뷰에서 백버튼 클릭시
        if( (keyCode == KeyEvent.KEYCODE_BACK) &&  webView.canGoBack() ){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class CustomWebViewClient extends WebViewClient {

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            try {
                Log.d("UrlLoading1", url);

                if (url.startsWith("app://")) {
                    Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    view.loadUrl(url);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
            //return super.shouldOverrideUrlLoading(view, url);
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            try {

                String url = request.getUrl().toString();
                Log.d("UrlLoading2", url);

                if (url.startsWith("app://")) {
                    Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    view.loadUrl(url);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
            //return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);

            sTitle = view.getTitle();

            Log.d("onPageFinished", sTitle);
        }

    }


    private class webJavaScriptInterface{
        Activity activity;

        webJavaScriptInterface(Activity activity){
            this.activity = activity;
        }

        @JavascriptInterface
        public void Javascript_makeText(String message){
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void Javascript_finish(){
            activity.finish();
        }
    }
}
