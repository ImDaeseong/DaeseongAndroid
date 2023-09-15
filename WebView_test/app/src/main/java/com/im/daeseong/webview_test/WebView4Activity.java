package com.im.daeseong.webview_test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class WebView4Activity extends AppCompatActivity {

    private static final String TAG = WebView4Activity.class.getSimpleName();

    public String sTitle;
    public Context context;
    private WebView webView;
    private webJavaScriptInterface webJavaScriptInterface;
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

        setContentView(R.layout.activity_web_view4);

        init();

        //네트워크 연결 여부
        if (isNetworkAvailable(this)) {
            initWebWebView("file:///android_asset/test3.html");
        } else {
            initWebWebView("about:blank");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();

        //백그라운드 실행시 - 웹화면 일시중지
        try {
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                    .invoke(webView, (Object[]) null);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //백그라운드 실행시 - 웹화면 재시작
        try {
            Class.forName("android.webkit.WebView")
                    .getMethod("onResume", (Class[]) null)
                    .invoke(webView, (Object[]) null);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //메모리 해제
        clearWebView();
    }

    private void init(){

        context = getApplicationContext();
        webView = (WebView)findViewById(R.id.webview1);
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
    }

    private void initWebWebView(String url) {

        // 웹뷰 세팅
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 자바 스크립트 사용

        webSettings.setLoadsImagesAutomatically(true);//웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정하는 속성
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//캐시모드를 사용하지 않고 네트워크를 통해서만 호출
        //webSettings.setAppCacheEnabled(false);//앱 내부 캐시 사용 여부 설정
        webSettings.setDomStorageEnabled(true);//로컬 스토리지 사용 여부를 설정하는 속성으로 팝업창등을 '하루동안 보지 않기' 기능 사용에 필요
        webSettings.setAllowFileAccess(false);// 웹 뷰 내에서 파일 액세스 활성화 여부

        //webSettings.setUseWideViewPort(true);//웹뷰에 맞게 출력하기
        //webSettings.setLoadWithOverviewMode(true);

        //WebView에서 확대/축소 가능
        webSettings.setBuiltInZoomControls(true); // 안드로이드 내장 줌 컨트롤 사용 X
        webSettings.setSupportZoom(true);//확대 축소 기능
        webSettings.setDisplayZoomControls(true);//줌 컨트롤 보이기/숨기기

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            webSettings.setTextZoom(100);
        }

        // API 레벨 16부터 이용할 수 있다.
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // javascript 가  window.open()을 사용할 수 있도록 설정
        webSettings.setSaveFormData(false); // 폼의 입력값를 저장하지 않는다
        webSettings.setSavePassword(false); // 암호를 저장하지 않는다.
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기

        webJavaScriptInterface = new webJavaScriptInterface(this);
        webView.addJavascriptInterface(webJavaScriptInterface, "Android");

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progressBar.setVisibility(View.GONE);

                sTitle = view.getTitle();
                Log.d("onPageFinished", sTitle);

                webJavaScriptInterface.Javascript_htmlTojavaScript();
                Log.e(TAG, "html에 있는 javascript 호출");
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                Log.d(TAG, request.getUrl().toString());

                return true;
                //return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                //error 400 보다 작으면 정상 처리
                if(errorCode < 400){
                    return;
                }

                webView.loadUrl("about:blank");
                progressBar.setVisibility(View.GONE);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                //error 400 보다 작으면 정상 처리
                if(error.getErrorCode() < 400){
                    return;
                }

                webView.loadUrl("about:blank");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);

                try {

                    String sTitle = String.format("<font color=\"#000000\">%s</font>", "알림");
                    String sMessage = String.format("<font color=\"#000000\">%s</font>", "이 사이트 보안 인증서는 신뢰하는 보안 인증서가 아닙니다.\n계속하시겠습니까?");

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.AlertDialogTheme);
                    builder.setTitle(Html.fromHtml(sTitle));
                    builder
                            .setMessage(Html.fromHtml(sMessage))
                            .setCancelable(false)
                            .setPositiveButton("계속",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            handler.proceed();
                                        }
                                    })
                            .setNegativeButton("취소",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            handler.cancel();
                                        }
                                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    Button btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    btnNegative.setTextColor(Color.BLACK);

                    Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    btnPositive.setTextColor(Color.BLACK);
                }catch (Exception ex){

                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                String sTitle = String.format("<font color=\"#000000\">%s</font>", "알림");
                String sMessage = String.format("<font color=\"#000000\">%s</font>", message);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.AlertDialogTheme);
                builder.setTitle(Html.fromHtml(sTitle));
                builder
                        .setMessage(Html.fromHtml(sMessage))
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.confirm();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                btnPositive.setTextColor(Color.BLACK);

                return true;
                //return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

                String sTitle = String.format("<font color=\"#000000\">%s</font>", "알림");
                String sMessage = String.format("<font color=\"#000000\">%s</font>", message);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.AlertDialogTheme);
                builder.setTitle(Html.fromHtml(sTitle));
                builder
                        .setMessage(Html.fromHtml(sMessage))
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 프로그램을 종료
                                        //EventActivity.this.finish();
                                        result.confirm();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소
                                        //dialog.cancel();
                                        result.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                btnNegative.setTextColor(Color.BLACK);

                Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                btnPositive.setTextColor(Color.BLACK);

                return true;
                //return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (progressBar.getVisibility() == View.GONE)
                        progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                Log.e(TAG, "title:" + title);
            }
        });

        webView.loadUrl(url);
    }

    private void clearWebView() {
        try {
            if (webView != null) {
                webView.removeAllViews();
                // in android 5.1(sdk:21) we should invoke this to avoid memory leak
                // see (https://coolpers.github.io/webview/memory/leak/2015/07/16/
                // android-5.1-webview-memory-leak.html)
                ((ViewGroup) webView.getParent()).removeView(webView);
                webView.setTag(null);
                webView.clearHistory();
                webView.destroy();
                webView = null;
            }
        }catch (Exception e){
        }
    }

    private class webJavaScriptInterface {

        Context mContext;

        webJavaScriptInterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void Javascript_htmlTojavaScript(){
            webView.loadUrl("javascript:EndLoad_Message()");
        }

        @JavascriptInterface
        public void Javascript_finish(){
            ((Activity) mContext).finish();
        }

        @JavascriptInterface
        public void Javascript_webToNative(String message){

            String sMsg = String.format("%s", message);
            Log.e(TAG, sMsg);
        }

        @JavascriptInterface
        public final String Javascript_getString(){
            return "html로 문자열 전달";
        }

        @JavascriptInterface
        public final int Javascript_getInt(){
            return 1;
        }

        @JavascriptInterface
        public final boolean Javascript_getBoolean(){
            return true;
        }
    }
}
