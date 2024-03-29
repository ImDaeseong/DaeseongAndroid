package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    String sUrl1 = "https://naver.com";
    String sUrl2 = "https://google.com";

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("텍스트 링크클릭 효과 \r\n텍스트 링크클릭 효과");

        settv1();
    }

    private void settv1(){

        SpannableString spannableString = new SpannableString(tv1.getText());
        spannableString.setSpan(new ClickableSpanEx(this, sUrl1), 4, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpanEx(this, sUrl2), 17, 22, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText((SpannableString) spannableString);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
    }

}