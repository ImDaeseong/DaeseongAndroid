package com.daeseong.spannable_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main6Activity.class.getSimpleName();

    private EditText et1;
    private TextView tv1;
    private Button button1;

    private String sEdit;

    private String sData = "서울\n" +
            "구름많음\n" +
            "온도 1.6°\n" +
            "미세 좋음\n" +
            "초미세 좋음\n" +
            "\"https://weather.naver.com/today/01110580?cpName=KMA\"\n" +
            "\n" +
            " 춘천\n" +
            "<!--구름많음-->\n" +
            "\"https://weather.naver.com/today/01150101?cpName=KMA\"\n" +
            "\n" +
            "강릉\n" +
            "<!--흐림-->\n" +
            "  온도 5.6° \"https://weather.naver.com/today/16113114?cpName=KMA\"\n" +
            "\n" +
            "청주\n" +
            "초미세 좋음 \"https://weather.naver.com/today/07170630?cpName=KMA\" <!--흐림-->\n" +
            "\n" +
            "대전\n" +
            "미세 보통\n" +
            "초미세 보통\n" +
            "\"https://weather.naver.com/today/06110517?cpName=KMA\"\n" +
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

                sEdit = et1.getText().toString();
                tv1.setText(sEdit);
            }
        });
    }

}