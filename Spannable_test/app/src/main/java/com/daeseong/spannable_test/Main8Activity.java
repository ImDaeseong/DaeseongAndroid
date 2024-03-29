package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class Main8Activity extends AppCompatActivity {

    private static final String TAG = Main8Activity.class.getSimpleName();

    private EditText et1;
    private TextView tv1;
    private Button button1;

    private String sEdit;

    private ArrayList<SpannedString> stringArray = new ArrayList<>();

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

    private boolean bHttp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        et1 = (EditText) findViewById(R.id.et1);
        tv1 = (TextView) findViewById(R.id.tv1);

        //textview scroll 추가
        tv1.setMovementMethod(new ScrollingMovementMethod());

        //데이터 검색 타입
        if(bHttp){
            et1.setText(sData1);
        } else {
            et1.setText(sData);
        }

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stringArray.clear();
                tv1.setText("");

                sEdit = et1.getText().toString();

                //데이터 검색 타입
                if(bHttp){
                    checkLinkhttps(sEdit);
                } else {
                    checkLink(sEdit);
                }

                SpannedString finalString = new SpannedString("");
                for (SpannedString item : stringArray) {
                    finalString = (SpannedString) TextUtils.concat(finalString, item);
                }
                tv1.setText(finalString);
                tv1.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    private void checkLinkhttps(String sInput){

        String slink1;
        String slink2 = "";
        String slink2_Sub;
        String slink3;
        String slink4;
        String slast;
        int nEnd = 0;

        String[] sRead = sInput.split("\n");
        for(int i=0; i < sRead.length; i++) {

            if(sRead[i]!=null) {

                String sCheck = sRead[i];

                if(sCheck.indexOf("https")>=0){

                    //링크 아닌부분
                    slink1 = sCheck.substring(0, sCheck.indexOf("https"));

                    //링크 부분
                    slast = sCheck.substring(sCheck.indexOf("https"));

                    nEnd = slast.indexOf(" ");
                    if(nEnd != -1 ){
                        slink2 = slast.substring(0, nEnd);
                    } else {
                        slink2 = slast;
                    }

                    //링크 끝나는 부분
                    if(nEnd == -1){
                        slink3 = "";
                    }else {
                        slink3 = slast.substring(nEnd+1);
                    }

                    SpannableString s1 = new SpannableString(slink1);

                    //링크 보여주는 부분
                    slink2_Sub = getNameURL(slink2);
                    SpannableString s2 = new SpannableString(slink2_Sub + " "); //여기에 공백을 하나 넎어야만 전체 라인 클릭이 않된다.
                    s2.setSpan(new ClickableSpanEx(this, slink2), 0 , slink2_Sub.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    SpannableString s3 = new SpannableString(slink3);

                    stringArray.add((SpannedString) TextUtils.concat(s1, s2, s3, "\n") );

                } else {

                    //링크 없는 라인
                    slink4 = sCheck;

                    SpannableString s4 = new SpannableString(slink4);
                    stringArray.add((SpannedString) TextUtils.concat("", s4, "\n") );
                }
            }
        }
    }

    private void checkLink(String sInput){

        String slink1 = "";
        String slink2 = "";
        String slink2_Sub = "";
        String slink3 = "";
        String slink4 = "";

        String[] sRead = sInput.split("\n");
        for(int i=0; i < sRead.length; i++) {

            if(sRead[i]!=null) {

                String sCheck = sRead[i];

                if(sCheck.indexOf("[")>=0 && sCheck.indexOf("]")>=0){

                    //링크 아닌부분
                    slink1 = sCheck.substring(0, sCheck.indexOf("["));

                    //링크 부분
                    slink2 = sCheck.substring(sCheck.indexOf("[")+1 , sCheck.indexOf("]"));

                    //링크 끝나는 부분
                    slink3 = sCheck.substring(sCheck.indexOf("]")+1);

                    SpannableString s1 = new SpannableString(slink1);

                    //링크 보여주는 부분
                    slink2_Sub = getNameURL(slink2);
                    SpannableString s2 = new SpannableString(slink2_Sub + " "); //여기에 공백을 하나 넎어야만 전체 라인 클릭이 않된다.
                    s2.setSpan(new ClickableSpanEx(this, slink2), 0 , slink2_Sub.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    SpannableString s3 = new SpannableString(slink3);

                    stringArray.add((SpannedString) TextUtils.concat(s1, s2, s3, "\n") );
                } else {

                    //링크 없는 라인
                    slink4 = sCheck;

                    SpannableString s4 = new SpannableString(slink4);
                    stringArray.add((SpannedString) TextUtils.concat("", s4, "\n") );
                }
            }
        }
    }

    private String getNameURL(String sInput){

        String sReturn = "";

        int nStart = sInput.lastIndexOf("/") + 1;
        int nEnd = sInput.lastIndexOf("?");

        if(nEnd < 0){
            nEnd = sInput.length();
        }

        sReturn = sInput.substring(nStart, nEnd);
        return sReturn;
    }
}