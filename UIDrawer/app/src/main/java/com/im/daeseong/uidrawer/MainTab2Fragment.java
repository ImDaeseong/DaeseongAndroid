package com.im.daeseong.uidrawer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import androidx.appcompat.app.AlertDialog;//import android.support.v7.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;//import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * Created by Daeseong on 2018-03-02.
 */

public class MainTab2Fragment extends Fragment {

    private static final String TAG = MainTab2Fragment.class.getSimpleName();

    private WebView wvWebView;
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = container.getContext();//getActivity().getApplicationContext();
        return inflater.inflate(R.layout.fragment2_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wvWebView = (WebView)view.findViewById(R.id.webview);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_progress_3), getResources().getColor(R.color.refresh_progress_2), getResources().getColor(R.color.refresh_progress_3));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try{
                    wvWebView.loadUrl(wvWebView.getUrl());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        try {
            initWebview("https://m.naver.com/");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        clearWebViewResource();
    }

    public void clearWebViewResource() {
        try {
            if (wvWebView != null) {
                wvWebView.removeAllViews();
                // in android 5.1(sdk:21) we should invoke this to avoid memory leak
                // see (https://coolpers.github.io/webview/memory/leak/2015/07/16/
                // android-5.1-webview-memory-leak.html)
                ((ViewGroup) wvWebView.getParent()).removeView(wvWebView);
                wvWebView.setTag(null);
                wvWebView.clearHistory();
                wvWebView.destroy();
                wvWebView = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initWebview(String url) {

        // 웹뷰 세팅
        WebSettings webSettings = wvWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 자바 스크립트 사용

        webSettings.setLoadsImagesAutomatically(true);//웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정하는 속성
        webSettings.setSupportZoom(false);//확대 축소 기능
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//캐시모드를 사용하지 않고 네트워크를 통해서만 호출
        //webSettings.setAppCacheEnabled(false);//앱 내부 캐시 사용 여부 설정
        webSettings.setDomStorageEnabled(true);//로컬 스토리지 사용 여부를 설정하는 속성으로 팝업창등을 '하루동안 보지 않기' 기능 사용에 필요
        webSettings.setUserAgentString("app");//웹에서 해당 속성을 통해 앱에서 띄운 웹뷰로 인지
        webSettings.setAllowFileAccess(true);// 웹 뷰 내에서 파일 액세스 활성화 여부

        webSettings.setUseWideViewPort(true);//웹뷰에 맞게 출력하기
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(false); // 안드로이드 내장 줌 컨트롤 사용 X

        // API 레벨 16부터 이용할 수 있다.
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // javascript 가  window.open()을 사용할 수 있도록 설정
        webSettings.setSaveFormData(false); // 폼의 입력값를 저장하지 않는다
        webSettings.setSavePassword(false); // 암호를 저장하지 않는다.
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기

        wvWebView.addJavascriptInterface(new webJavaScriptInterface(mContext), "Android");


        //WebViewClient
        wvWebView.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                try {
                    Log.e(TAG,"url:" + url);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
                // return super.shouldOverrideUrlLoading(view, url);
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                try {
                    String url = request.getUrl().toString();
                    Log.e(TAG,"request url:" + url);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
                //return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                wvWebView.loadUrl("about:blank");
                swipeRefreshLayout.setRefreshing(false);

                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        // //웹뷰에서 자바스크립트 alert과 confirm 이 동작하게 처리
        wvWebView.setWebChromeClient(new WebChromeClient() {

            //alert 처리
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("알림1")
                        .setMessage(message)
                        .setPositiveButton("네",
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
            };

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
                //return super.onJsConfirm(view, url, message, result);
            };

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            };

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    swipeRefreshLayout.setRefreshing(false);

                } else {

                    if (!swipeRefreshLayout.isRefreshing())
                        swipeRefreshLayout.setRefreshing(true);
                }

                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                //webview 제목
                try{
                    Log.e(TAG, "onReceivedTitle:" + title);//tvTitle.setText(title);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //백버튼 클릭시 이전 페이지로 이동
        wvWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvWebView.canGoBack()) {

                        Log.e(TAG, "setOnKeyListener");
                        wvWebView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

        wvWebView.loadUrl(url);
    }

    // 안드로이드와 자바스크립트간의 데이터 주고 받기
    public class webJavaScriptInterface {
        Context mContext;

        webJavaScriptInterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void Javascript_makeText(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void Javascript_finish(){
            ((Activity) mContext).finish();
        }
    }

}