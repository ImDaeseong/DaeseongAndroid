package com.im.daeseong.webview_test;

import android.annotation.TargetApi;
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
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebView1Activity extends AppCompatActivity {

    private static final String TAG = WebView1Activity.class.getSimpleName();

    public String sTitle;
    public Context context;
    private WebView webView;

    public static boolean isNetworkAvailable(Context context)
    {
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

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setTheme(android.R.style.Theme.NoTitleBar);

        setContentView(R.layout.activity_web_view1);
        context = getApplicationContext();

        webView = (WebView)findViewById(R.id.webview1);
        webView.getSettings().setJavaScriptEnabled(true);//웹뷰에서 자바스크립트 실행 가능

        //webView.clearCache(true);//캐시 지우기
        //webView.reload();//현재 웹뷰 새로고침
        //webView.stopLoading();//로딩 중단

        //스크롤 없애기기
        /*
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        */

        webView.setWebViewClient(new CustomWebViewClient());

        //웹뷰에서 자바스크립트 alert과 confirm 이 동작하게 처리
        webView.setWebChromeClient(new WebChromeClient(){

            //alert 처리
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("알림")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
                //return super.onJsAlert(view, url, message, result);
            }

            //confirm 처리
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("알림")
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
                //return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                Log.e(TAG, "title:" + title);
            }
        });


        //네트워크 연결 여부
        if(isNetworkAvailable(this)){
            webView.loadUrl("file:///android_asset/test1.html");
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

    private class CustomWebViewClient extends WebViewClient{

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Log.d("test1.html 에서 링크 클릭시:", url);

            try {

                if (url.startsWith("app://")) {
                    Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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

            Log.d("test1.html 에서 링크 클릭시:", request.getUrl().toString());

            try {

                String url = request.getUrl().toString();

                if (url.startsWith("app://")) {
                    Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            sTitle = view.getTitle();

            Log.d("onPageFinished", sTitle);
        }
    }

}
