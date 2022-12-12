package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class Main9Activity extends AppCompatActivity {

    private static final String TAG = Main9Activity.class.getSimpleName();

    private EditText et1;
    private TextView tv1;
    private Button button1;

    private String sEdit;
    private ArrayList<String> list = new ArrayList<>();

    private String sData = "서울          서울         서울\n" +
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
        setContentView(R.layout.activity_main9);

        et1 = (EditText) findViewById(R.id.et1);
        tv1 = (TextView) findViewById(R.id.tv1);

        //textview scroll 추가
        tv1.setMovementMethod(new ScrollingMovementMethod());

        //데이터 검색 타입
        et1.setText(sData);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText("");
                sEdit = et1.getText().toString();
                tv1.setText(sEdit);

                setLink(tv1.getText().toString());
            }
        });
    }

    private void setLink(String sValue){

        SpannableString spannableString = new SpannableString(sValue);

        list.clear();
        getLinkList(sValue);

        for (int i=0; i < list.size(); i++){

            String sLink = list.get(i);
            spannableString.setSpan(new ClickableSpanEx(this, sLink), sValue.indexOf(sLink), sValue.indexOf(sLink) + sLink.length(), 0);
        }

        tv1.setText((SpannableString) spannableString);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void getLinkList(String sInput){

        String slink;
        String slast;
        int nEnd = 0;

        String[] sRead = sInput.split("\n");
        for(int i=0; i < sRead.length; i++) {

            if(sRead[i]!=null) {

                String sCheck = sRead[i];

                if(sCheck.indexOf("https")>=0){

                    //링크 부분
                    slast = sCheck.substring(sCheck.indexOf("https"));

                    nEnd = slast.indexOf(" ");
                    if(nEnd != -1 ){
                        slink = slast.substring(0, nEnd);
                    } else {
                        slink = slast;
                    }

                    list.add(slink);
                }
            }
        }
    }

}