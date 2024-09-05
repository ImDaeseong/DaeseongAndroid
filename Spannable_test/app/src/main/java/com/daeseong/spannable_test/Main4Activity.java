package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private LinearLayout lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        lv = findViewById(R.id.lv);

        settv1();
        settv2();
        settv3();
        settv4();
        settv5();
        settv6();
        settv7();
        settv8();
    }

    private void settv1(){

        //글자색
        SpannableString spannableString1 = new SpannableString("텍스트 링크클릭 효과1");
        spannableString1.setSpan(new ForegroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //TextView 생성
        TextView tv1 = new TextView(this);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //tv1.setBackgroundColor(Color.RED);
        tv1.setText((SpannableString)spannableString1);

        //추가
        lv.addView(tv1);
    }

    private void settv2() {

        //배경색
        SpannableString spannableString2 = new SpannableString("텍스트 링크클릭 효과2");
        spannableString2.setSpan(new BackgroundColorSpan(Color.RED), 0, 3, 0);

        //TextView 생성
        TextView tv2 = new TextView(this);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv2.setText((SpannableString) spannableString2);

        //추가
        lv.addView(tv2);
    }

    private void settv3() {

        //밑줄 효과
        SpannableString spannableString3 = new SpannableString("텍스트 링크클릭 효과3");
        spannableString3.setSpan(new UnderlineSpan(), 4, 6, 0);

        //TextView 생성
        TextView tv3 = new TextView(this);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv3.setText((SpannableString) spannableString3);

        //추가
        lv.addView(tv3);
    }

    private void settv4() {

        //링크 효과
        SpannableString spannableString4 = new SpannableString("텍스트 링크클릭 효과4");
        spannableString4.setSpan(new URLSpan("https://naver.com"), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //TextView 생성
        TextView tv4 = new TextView(this);
        tv4.setTextSize(20);
        tv4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv4.setText((SpannableString) spannableString4);

        //추가
        lv.addView(tv4);
    }

    private void settv5(){

        //링크 클릭 효과
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("텍스트 링크클릭 효과5");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"));
                startActivity(intent);
            }
        };
        spannableStringBuilder.setSpan(clickableSpan,4,9,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //TextView 생성
        TextView tv5 = new TextView(this);
        tv5.setTextSize(20);
        tv5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv5.setText(spannableStringBuilder);

        //클릭 가능하게 설정
        tv5.setMovementMethod(LinkMovementMethod.getInstance());

        //추가
        lv.addView(tv5);
    }

    private void settv6() {

        //html 효과
        String sHtml = "<b>"+"html 테스트1"+"</b>";

        //TextView 생성
        TextView tv6 = new TextView(this);
        tv6.setTextSize(20);
        tv6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv6.setText(Html.fromHtml(sHtml));

        //추가
        lv.addView(tv6);
    }

    private void settv7() {

        String sMsg = "html 테스트2";

        //html 효과
        String sHtml =  "<font color='#66ccff'>"+sMsg+"</font>";

        //TextView 생성
        TextView tv7 = new TextView(this);
        tv7.setTextSize(20);
        tv7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv7.setText(Html.fromHtml(sHtml));

        //추가
        lv.addView(tv7);
    }

    private void settv8() {

        String sMsg = "html 테스트3";

        //html 효과
        String sHtml =  "<a href='https://naver.com'><font color='#66ccff'>"+sMsg+"</font></a>";

        //TextView 생성
        TextView tv8 = new TextView(this);
        tv8.setTextSize(20);
        tv8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv8.setText(Html.fromHtml(sHtml));

        //클릭 가능하게 설정
        tv8.setMovementMethod(LinkMovementMethod.getInstance());

        //추가
        lv.addView(tv8);
    }
}