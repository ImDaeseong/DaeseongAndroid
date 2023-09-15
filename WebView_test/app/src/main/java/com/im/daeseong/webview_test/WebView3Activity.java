package com.im.daeseong.webview_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebView3Activity extends AppCompatActivity {

    private static final String TAG = WebView3Activity.class.getSimpleName();

    public Context context;
    private WebView webView;

    private BackPressCloseHandler backPressCloseHandler;

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

        //백버튼 2번 클릭시 종료
        backPressCloseHandler = new BackPressCloseHandler(WebView3Activity.this);

        setContentView(R.layout.activity_web_view3);

        context = getApplicationContext();

        webView = (WebView)findViewById(R.id.webview1);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setAppCacheEnabled(true);
        webView.setWebViewClient(new CustomWebViewClient());

        //네트워크 연결 여부
        if (isNetworkAvailable(this)) {
            webView.loadUrl("http://m.naver.com");
        } else {
            webView.loadUrl("about:blank");
        }

        // 쿠키 즉시 싱크를 위한 싱크매니저 등록
        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager.getInstance().setAcceptCookie(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 쿠키 즉시 싱크 시작
        CookieSyncManager.getInstance().startSync();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 쿠키 즉시 싱크 중지
        CookieSyncManager.getInstance().stopSync();
    }

    @Override
    public void onBackPressed() {

        backPressCloseHandler.onBackPressed();
        //super.onBackPressed();
    }

    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("Url", failingUrl);
            intent.putExtra("ErrCode", errorCode);
            intent.putExtra("Desdescription", description);
            startActivity(intent);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if(url.startsWith("http://127.0.0.1")){
                if(url.contains("/login")){

                }
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            // 하나의 페이지 전환이 일어날 때마다, 쿠키를 즉시 싱크하도록 한다.
            CookieSyncManager.getInstance().sync();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }

    private class BackPressCloseHandler {
        private long backKeyPressedTime = 0;
        private Toast toast;
        private Activity activity;

        public BackPressCloseHandler(Activity activity){
            this.activity = activity;
        }

        public void onBackPressed(){
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showmakeText();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                toast.cancel();
                activity.finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }

        private void showmakeText(){
            toast = Toast.makeText(activity, "'뒤로'버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
