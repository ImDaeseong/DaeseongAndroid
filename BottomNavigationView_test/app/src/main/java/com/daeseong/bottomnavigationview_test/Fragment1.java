package com.daeseong.bottomnavigationview_test;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Fragment1 extends Fragment {

    private static final String TAG = Fragment1.class.getSimpleName();

    private WebView webView;

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Log.e(TAG, "onCreateView");

        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Log.e(TAG, "onViewCreated");

        webView = (WebView)view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //Log.e(TAG, "onDestroyView");
    }

    @Override
    public void onPause() {
        super.onPause();

        //Log.e(TAG, "Fragment 가려질때");
    }

    @Override
    public void onResume() {
        super.onResume();

        //Log.e(TAG, "Fragment 보일때");
    }
}
