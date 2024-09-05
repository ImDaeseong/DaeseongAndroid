package com.daeseong.spannable_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("텍스트 링크클릭 효과 \r\n텍스트 링크클릭 효과");

        settv1();
    }

    private void settv1(){

        Spannable spannable = (Spannable) tv1.getText();
        String sUrl = "https://naver.com";
        spannable.setSpan(new URLSpan(sUrl), 4, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //1 link click
        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sUrl));
                startActivity(intent);
            }
        }, 4, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //2 link click
        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sUrl));
                startActivity(intent);
            }
        }, 17, 22, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //클릭 가능하게 설정
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
    }
}