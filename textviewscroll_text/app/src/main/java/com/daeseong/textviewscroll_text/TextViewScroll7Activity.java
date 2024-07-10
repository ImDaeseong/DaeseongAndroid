package com.daeseong.textviewscroll_text;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TextViewScroll7Activity extends AppCompatActivity {

    private static final String TAG = TextViewScroll7Activity.class.getSimpleName();

    private View cL1, cL2;
    private TextScrollerCL textScrollerCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_scroll7);

        cL1 = findViewById(R.id.cL1);
        cL2 = findViewById(R.id.cL2);

        //스크롤 클래스
        textScrollerCL = new TextScrollerCL(cL1, cL2);
        //textScrollerCL.setDirect(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (textScrollerCL != null) {
            textScrollerCL.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (textScrollerCL != null) {
            textScrollerCL.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (textScrollerCL != null) {
            textScrollerCL.start();
        }
    }
}