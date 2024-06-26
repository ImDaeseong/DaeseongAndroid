package com.daeseong.textviewscroll_text;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewScroll4Activity extends AppCompatActivity {

    private static final String TAG = TextViewScroll4Activity.class.getSimpleName();

    private TextView tv1;
    private TextView tv2;
    private TextScrollerAni textScrollerAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_scroll4);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        //스크롤 클래스
        textScrollerAni = new TextScrollerAni(tv1, tv2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (textScrollerAni != null) {
            textScrollerAni.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (textScrollerAni != null) {
            textScrollerAni.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (textScrollerAni != null) {
            textScrollerAni.start();
        }
    }
}