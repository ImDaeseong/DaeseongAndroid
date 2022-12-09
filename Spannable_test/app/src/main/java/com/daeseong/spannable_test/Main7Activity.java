package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;

public class Main7Activity extends AppCompatActivity {

    private static final String TAG = Main7Activity.class.getSimpleName();

    private EditText et1;
    private TextView tv1;
    private Button button1;
    private Button button2;

    private String sEdit;

    private HashMap<String, String> linkmap;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        et1 = (EditText) findViewById(R.id.et1);
        tv1 = (TextView) findViewById(R.id.tv1);

        //textview scroll 추가
        tv1.setMovementMethod(new ScrollingMovementMethod());

        et1.setText(sData);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText("");

                sEdit = et1.getText().toString();
                readLink(sEdit);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText("");

                sEdit = et1.getText().toString();

                String slink = checkLink(sEdit);
                setTextViewLink(slink);
            }
        });
    }

    //첫번째
    private void readLink(String sInput){

        String slink1;
        String slink2;
        String slink3;
        String slink4;
        String sHtml;
        String sTotal = "";

        String sIndex;
        int nIndex = 0;

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

                    //링크 자체 텍스트
                    //sHtml = "<a href=" +"'" +slink2+ "'" + "><font color='#66ccff'>" + slink2 + "</font></a>";

                    //링크 다른 문자열로 변경
                    nIndex ++;
                    sIndex = String.format("Index%d", nIndex);
                    sHtml = "<a href=" +"'" +slink2+ "'" + "><font color='#66ccff'>" + sIndex + "</font></a>";

                    sTotal += slink1 + sHtml + slink3 + "<br>";

                } else {

                    //링크 없는 라인
                    slink4 = sCheck;
                    sTotal +=  slink4 + "<br>";
                }
            }
        }

        tv1.setText(Html.fromHtml(sTotal));
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //두번째
    private void setTextViewLink(String sInput){

        String slink1;
        String slink2;
        String slink3;
        String slink4;
        String slinkName;
        String sHtml;
        String sTotal = "";

        String sIndex;
        int nIndex = 0;

        String[] sRead = sInput.split("\n");
        for(int i=0; i < sRead.length; i++) {

            if(sRead[i]!=null) {

                String sCheck = sRead[i];

                if(sCheck.contains("Index")){

                    nIndex ++;
                    sIndex = String.format("Index%d", nIndex);

                    //링크 아닌부분
                    slink1 = sCheck.substring(0, sCheck.indexOf(sIndex));

                    //링크 부분
                    slink2 = linkmap.get(sIndex);

                    //링크 끝나는 부분 IndexN 뒤에 모든 문자열
                    slink3 = sCheck.substring(sCheck.lastIndexOf(sIndex) + 6);

                    //링크 이름
                    slinkName = getNameURL(slink2);

                    sHtml = "<a href=" +"'" +slink2+ "'" + "><font color='#66ccff'>" + slinkName + "</font></a>";

                    sTotal += slink1 + sHtml + slink3 + "<br>";

                } else {

                    //링크 없는 라인
                    slink4 = sCheck;
                    sTotal +=  slink4 + "<br>";
                }
            }
        }
        tv1.setText(Html.fromHtml(sTotal));
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
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

    private String removeTags(String sInput)  {

        String sReturn = "";

        int nStart = sInput.indexOf("[");
        int nEnd =  sInput.indexOf("]");

        if (nStart >= 0) {
            sReturn = sInput.substring(nStart + 1, nEnd);
        }
        return sReturn;
    }

    private String checkLink(String sInput){

        String slink1;
        String slink2;
        String slink3;
        String slink4;
        String sTotal = "";

        String sIndex;
        int nIndex = 0;

        if(linkmap == null){
            linkmap = new HashMap();
        }else {
            linkmap.clear();
        }

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
                    slink3 = sCheck.substring(sCheck.indexOf("]")+1 , sCheck.length());

                    //링크 다른 문자열로 변경
                    nIndex ++;
                    sIndex = String.format("Index%d", nIndex);
                    linkmap.put(sIndex, slink2);

                    sTotal += slink1 + sIndex + slink3 + "\n";

                } else {

                    //링크 없는 라인
                    slink4 = sCheck;
                    sTotal +=  slink4 + "\n";
                }
            }
        }
        return sTotal;
    }
}