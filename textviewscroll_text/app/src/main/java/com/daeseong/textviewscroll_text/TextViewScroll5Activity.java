package com.daeseong.textviewscroll_text;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewScroll5Activity extends AppCompatActivity {

    private static final String TAG = TextViewScroll2Activity.class.getSimpleName();

    private TextView tv1, tv2;
    private TextScrollerEx textScrollerEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_scroll5);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        //스크롤 클래스
        textScrollerEx = new TextScrollerEx(tv1, tv2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (textScrollerEx != null) {
            textScrollerEx.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (textScrollerEx != null) {
            textScrollerEx.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (textScrollerEx != null) {
            textScrollerEx.start();
        }
    }
}