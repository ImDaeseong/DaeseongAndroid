package com.daeseong.textviewscroll_text;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewScroll3Activity extends AppCompatActivity {

    private static final String TAG = TextViewScroll3Activity.class.getSimpleName();

    private TextView tv1, tv2;
    private TextScroller textScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_scroll3);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        //스크롤 클래스
        textScroller = new TextScroller(tv1, tv2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (textScroller != null) {
            textScroller.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (textScroller != null) {
            textScroller.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (textScroller != null) {
            textScroller.start();
        }
    }

}



