package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main6Activity.class.getSimpleName();

    private EditText et1;
    private TextView tv1;
    private Button button1;
    private Button button2;
    private Button button3;

    private String sEdit;

    private String sData = "서울          서울         서울\n" +
            "구름많음\n" +
            "온도 1.6°\n" +
            "미세 좋음\n" +
            "초미세 좋음\n" +
            "[https://weather.naver.com/today/01110580?cpName=KMA] 링크 처리 1 \n" +
            "\n" +
            " 춘천         춘천 \n" +
            "<!--구름많음-->\n" +
            "   링크 처리 2 [https://weather.naver.com/today/01150101?cpName=KMA]\n" +
            "\n" +
            "강릉__강릉\n" +
            "<!--흐림-->\n" +
            "  온도 5.6° [https://weather.naver.com/today/16113114?cpName=KMA]   링크 처리 3\n" +
            "\n" +
            "!청주       청주!\n" +
            "초미세 좋음 [https://weather.naver.com/today/07170630?cpName=KMA] 링크 처리 4  <!--흐림-->\n" +
            "\n" +
            "@대전                            대전#\n" +
            "미세 보통\n" +
            "초미세 보통\n" +
            "링크 처리 5 [https://weather.naver.com/today/06110517?cpName=KMA]   링크 처리 5\n" +
            "\n";

    private String sData1 = "서울          서울         서울\n" +
            "구름많음\n" +
            "온도 1.6°\n" +
            "미세 좋음\n" +
            "초미세 좋음\n" +
            "https://weather.naver.com/today/01110580?cpName=KMA 링크 처리 1 \n" +
            "\n" +
            " 춘천         춘천 \n" +
            "<!--구름많음-->\n" +
            "   링크 처리 2 https://weather.naver.com/today/01150101?cpName=KMA\n" +
            "\n" +
            "강릉__강릉\n" +
            "<!--흐림-->\n" +
            "  온도 5.6° https://weather.naver.com/today/16113114?cpName=KMA   링크 처리 3\n" +
            "\n" +
            "!청주       청주!\n" +
            "초미세 좋음 https://weather.naver.com/today/07170630?cpName=KMA 링크 처리 4  <!--흐림-->\n" +
            "\n" +
            "@대전                            대전#\n" +
            "미세 보통\n" +
            "초미세 보통\n" +
            "링크 처리 5 https://weather.naver.com/today/06110517?cpName=KMA   링크 처리 5\n" +
            "\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        et1 = (EditText) findViewById(R.id.et1);
        tv1 = (TextView) findViewById(R.id.tv1);

        //textview scroll 추가
        tv1.setMovementMethod(new ScrollingMovementMethod());

        et1.setText(sData);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sEdit = CheckReturn(et1.getText().toString());
                tv1.setText(sEdit);
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckLink(et1.getText().toString());
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et1.setText(sData1);

                CheckHttp(et1.getText().toString());
            }
        });
    }

    private void CheckLink(String sInput){

        //[] 감싼 부분이 링크
        SpannableString spannableString = new SpannableString(sInput);
        Matcher matcher = Pattern.compile("\\[[^\\]]+\\]").matcher(sInput);

        String sFind, sUrl;
        int nLength;
        while (matcher.find()) {
            sFind = matcher.group();
            nLength = sFind.length();
            sUrl = sFind.substring(1, nLength - 1);
            spannableString.setSpan(new ClickableSpanEx(this, sUrl), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tv1.setText((SpannableString) spannableString);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void CheckHttp(String sInput){

        String sRegex = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";

        SpannableString spannableString = new SpannableString(sInput);
        Matcher matcher = Pattern.compile(sRegex).matcher(sInput);

        String sFind, sUrl;
        int nLength;
        while (matcher.find()) {
            sFind = matcher.group();
            nLength = sFind.length();
            sUrl = sFind.substring(0, nLength);
            spannableString.setSpan(new ClickableSpanEx(this, sUrl), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tv1.setText((SpannableString) spannableString);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private String CheckReturn(String sInput){

        //\n(줄바꿈) 기준으로 문자열 정리
        SpannableString spannableString = new SpannableString(sInput);
        Pattern pattern = Pattern.compile("\n");
        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            spannableString.setSpan(new AbsoluteSizeSpan(15, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString.toString();
    }
}