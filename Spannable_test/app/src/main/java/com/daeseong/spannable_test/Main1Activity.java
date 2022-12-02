package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("텍스트 색상 변경");

        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText("텍스트 색상 변경");

        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setText("텍스트 색상 변경");

        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setText("텍스트 색상 변경");

        settv1();
        settv2();
        settv3();
        settv4();
    }

    private void settv1(){

        Spannable spannable = (Spannable) tv1.getText();
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE); //글자색
    }

    private void settv2(){

        Spannable spannable = (Spannable) tv2.getText();
        spannable.setSpan(new BackgroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE); //배경색
    }

    private void settv3(){

        Spannable spannable = (Spannable) tv3.getText();
        spannable.setSpan(new UnderlineSpan(), 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //밑줄
    }

    private void settv4(){

        Spannable spannable = (Spannable) tv4.getText();
        spannable.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE); //글자색
        spannable.setSpan(new BackgroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE); //배경색
        spannable.setSpan(new UnderlineSpan(), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //밑줄
    }
}