package com.daeseong.flexboxlayout_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FlexboxLayout1Activity extends AppCompatActivity {

    private TextView t1, t2, t3, t4, t5;
    private EditText et1, et2, et3, et4, et5;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox_layout1);

        init();
        initData();
    }

    private void init() {

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        et3 = (EditText)findViewById(R.id.et3);
        et4 = (EditText)findViewById(R.id.et4);
        et5 = (EditText)findViewById(R.id.et5);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t1.setText(et1.getText());
                t2.setText(et2.getText());
                t3.setText(et3.getText());
                t4.setText(et4.getText());
                t5.setText(et5.getText());
            }
        });
    }

    private void initData() {

        t1.setText("입력값1");
        et1.setText("입력값1");

        t2.setText("입력값2");
        et2.setText("입력값2");

        t3.setText("자동 줄바꿈 입력문자열3");
        et3.setText("자동 줄바꿈 입력문자열3");

        t4.setText("TextView4");
        et4.setText("TextView4");

        t5.setText("마지막 라인 Textview5");
        et5.setText("마지막 라인 Textview5");
    }
}