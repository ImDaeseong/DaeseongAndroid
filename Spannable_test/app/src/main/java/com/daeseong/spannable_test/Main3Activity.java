package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("텍스트 색상 변경");

        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText("텍스트 색상 변경");

        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setText("텍스트 색상 변경");

        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setText("텍스트 색상 변경");

        tv5 = (TextView) findViewById(R.id.tv5);
        tv5.setText("텍스트 색상 변경");

        tv6 = (TextView) findViewById(R.id.tv6);
        tv6.setText("텍스트 이미지 추가");

        tv7 = (TextView) findViewById(R.id.tv7);
        tv7.setText("텍스트 색상 볼드 처리");

        settv1();
        settv2();
        settv3();
        settv4();
        settv5();
        settv6();
        settv7();
    }

    private void settv1(){

        SpannableString spannableString = new SpannableString(tv1.getText());
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 3, 0);
        tv1.setText(spannableString);
    }

    private void settv2(){

        SpannableString spannableString = new SpannableString(tv2.getText());
        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 0, 3, 0);
        tv2.setText(spannableString);
    }

    private void settv3(){

        SpannableString spannableString = new SpannableString(tv3.getText());
        spannableString.setSpan(new UnderlineSpan(), 4, 6, 0);
        tv3.setText(spannableString);
    }

    private void settv4(){

        SpannableString spannableString = new SpannableString(tv4.getText());
        spannableString.setSpan(new URLSpan("https://naver.com"), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv4.setText(spannableString);
    }

    private void settv5(){

        SpannableString spannableString = new SpannableString(tv5.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"));
                startActivity(intent);
            }
        };
        spannableString.setSpan(clickableSpan,4,9,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv5.setText(spannableString);

        //클릭 가능하게 설정
        tv5.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void settv6(){

        Drawable drawable = getResources().getDrawable(R.drawable.number1, null);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        SpannableString spannableString = new SpannableString(tv6.getText());
        spannableString.setSpan(new ImageSpan(drawable), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        tv6.setText(spannableString);
    }

    private void settv7(){

        //문장에서 찾을 문자열
        String sFind = "볼드";
        int nStart = tv7.getText().toString().indexOf(sFind);
        int nEnd = nStart + sFind.length();

        SpannableString spannableString = new SpannableString(tv7.getText());
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), nStart, nEnd, 0);
        spannableString.setSpan(new BackgroundColorSpan(Color.RED), nStart, nEnd, 0);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), nStart, nEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv7.setText(spannableString);
    }
}