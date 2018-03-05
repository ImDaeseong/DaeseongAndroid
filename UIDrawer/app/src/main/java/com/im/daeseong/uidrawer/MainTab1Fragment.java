package com.im.daeseong.uidrawer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Daeseong on 2018-03-02.
 */

public class MainTab1Fragment extends Fragment {

    WebView webviewTab1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1_main, container, false);

        webviewTab1 = view.findViewById(R.id.webviewTab1);
        //webviewTab1.getSettings().setDefaultTextEncodingName("UTF-8");
        webviewTab1.getSettings().setJavaScriptEnabled(true);//웹뷰에서 자바스크립트 실행 가능
        webviewTab1.getSettings().setAppCacheEnabled(true);

        //스크롤 없애기기
        /*
        webviewTab1.setVerticalScrollBarEnabled(false);
        webviewTab1.setHorizontalScrollBarEnabled(false);
        */
        webviewTab1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//내용물의 안쪽에 투명하게 스크롤바 생성

        //webviewTab1.addJavascriptInterface(new webJavaScriptInterface(this), "Android");

        webviewTab1.setWebViewClient(new CustomWebViewClient());

        //웹뷰에서 자바스크립트 alert과 confirm 이 동작하게 처리
        webviewTab1.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }

            //alert 처리
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            //confirm 처리
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });

        webviewTab1.loadUrl("http://m.naver.com");

       return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public class webJavaScriptInterface{
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

    private class CustomWebViewClient extends WebViewClient {

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
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
        }

    }

}
