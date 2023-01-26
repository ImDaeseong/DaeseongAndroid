package com.im.daeseong.banner_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StringutilActivity extends AppCompatActivity {

    private static final String TAG = StringutilActivity.class.getSimpleName();

    private Button button1, button2, button3, button4, button5, button6, button7, button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stringutil);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2023-01-26";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2023-01-25";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2023-01-06";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2022-11-06";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2022-10-28";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2022-08-16";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2021-08-08";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });

        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sInput = "2020-08-10";
                String sResult = String_util.getLastVisitDay(sInput);
                Log.e(TAG, sResult);
            }
        });
    }
}