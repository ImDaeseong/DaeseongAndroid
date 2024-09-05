package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TextView tv1, tv2, tv3, tv4, tv5, tv6;

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

        tv5 = (TextView) findViewById(R.id.tv5);
        tv5.setText("텍스트 이미지 추가");

        tv6 = (TextView) findViewById(R.id.tv6);
        tv6.setText("텍스트 색상 볼드 처리");

        settv1();
        settv2();
        settv3();
        settv4();
        settv5();
        settv6();
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

    private void settv5(){

        Drawable drawable = getResources().getDrawable(R.drawable.number1, null);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        Spannable spannable = (Spannable) tv5.getText();
        spannable.setSpan(new ImageSpan(drawable), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private void settv6(){

        //문장에서 찾을 문자열
        String sFind = "볼드";
        int nStart = tv6.getText().toString().indexOf(sFind);
        int nEnd = nStart + sFind.length();

        Spannable spannable = (Spannable) tv6.getText();
        spannable.setSpan(new ForegroundColorSpan(Color.WHITE), nStart, nEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new BackgroundColorSpan(Color.RED), nStart, nEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), nStart, nEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}