package com.daeseong.coordinatorlayout_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.material.appbar.AppBarLayout;

public class coordinatorlayout4Activity extends AppCompatActivity {

    private static final String TAG = coordinatorlayout4Activity.class.getSimpleName();

    private AppBarLayout aB1;
    private WebView web1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout4);

        aB1 = findViewById(R.id.aB1);
        aB1.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener<AppBarLayout>() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    Log.e(TAG, "툴바가 완전히 펼쳐진 상태");
                } else {
                    Log.e(TAG, "툴바가 축소된 상태");
                }
            }
        });

        web1 = (WebView)findViewById(R.id.web1);
        web1.getSettings().setJavaScriptEnabled(true);
        web1.setWebViewClient(new WebViewClient());
        web1.loadUrl("https://m.naver.com/");
    }
}