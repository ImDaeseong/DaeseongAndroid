package com.daeseong.textviewscroll_text;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class TextViewScroll1Activity extends AppCompatActivity {

    private static final String TAG = TextViewScroll1Activity.class.getSimpleName();

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_scroll1);

        tv1 = findViewById(R.id.tv1);

        //내용이 꽉찬 상태에서만 자동으로 작동
        tv1.setText("TextViewScroll1Activity TextViewScroll1Activity TextViewScroll1Activity");

        tv1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv1.setSelected(true);
    }

}