package com.daeseong.library_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.daeseong.stringutilslib.Stringutils;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String sInput;
    private double dInput;
    private int nType;
    private Date dDate;
    private String sResult;

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "https://daeseong.com/board/mainfolder?index=1";
                sResult = Stringutils.getlastStringUrl(sInput);
                Log.e(TAG, "getlastStringUrl:" + sResult);

            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "https://daeseong.com/board/mainfolder?index=1";
                sResult = Stringutils.getSubStringUrl(sInput);
                Log.e(TAG, "getFileNameURL:" + sResult);
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "https://daeseong.com/board/mainfolder?index=test.exe";
                sResult = Stringutils.getStringExt(sInput);
                Log.e(TAG, "getStringExt:" + sResult);
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "https://daeseong.com/board/mainfolder?index=1";
                sResult = Stringutils.getStringUpper(sInput);
                Log.e(TAG, "getStringUpper:" + sResult);
            }
        });

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "https://daeseong.com/board/mainfolder?index=test.exe";
                sResult = Stringutils.getStringLower(sInput);
                Log.e(TAG, "getStringLower:" + sResult);
            }
        });

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "3154055";
                sResult = Stringutils.getStringDecimalFormat(sInput);
                Log.e(TAG, "getStringDecimalFormat:" + sResult);

            }
        });

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dInput = 3154055;
                sResult = Stringutils.getDoubleCovnertToString(dInput);
                Log.e(TAG, "getDoubleCovnertToString:" + sResult);
            }
        });

        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nType = 1;
                sResult = Stringutils.getStringTime(nType);
                Log.e(TAG, "getStringTime:" + sResult);

                nType = 2;
                sResult = Stringutils.getStringTime(nType);
                Log.e(TAG, "getStringTime:" + sResult);

                nType = 3;
                sResult = Stringutils.getStringTime(nType);
                Log.e(TAG, "getStringTime:" + sResult);

                nType = 4;
                sResult = Stringutils.getStringTime(nType);
                Log.e(TAG, "getStringTime:" + sResult);

                nType = 5;
                sResult = Stringutils.getStringTime(nType);
                Log.e(TAG, "getStringTime:" + sResult);
            }
        });

        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nType = 3;
                sResult = Stringutils.getStringTime(nType);
                Log.e(TAG, "getStringTime:" + sResult);

                dDate = Stringutils.getStringToDate(sResult, nType);
                Log.e(TAG, "getStringToDate:" + dDate.toString());

                sResult = Stringutils.getDateToString(dDate, nType);
                Log.e(TAG, "getDateToString:" + sResult);
            }
        });

        button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "\"test\"";
                sResult = Stringutils.removeStringQuoted(sInput);
                Log.e(TAG, "removeStringQuoted:" + sResult);
            }
        });

        button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "test1,test2,test3";
                String[] sArray = Stringutils.splitString(sInput, ",");
                for(int i=0; i < sArray.length; i++){
                    Log.e(TAG, "splitString:" + sArray[i]);
                }
            }
        });

        button12 = findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "010-1100-7001";
                if(Stringutils.isPhone(sInput)){
                    Log.e(TAG, "isPhone number");
                }else{
                    Log.e(TAG, "Not isPhone number");
                }
            }
        });

        button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "test@gmail.com";
                if(Stringutils.isEmail(sInput)){
                    Log.e(TAG, "isEmail number");
                }else{
                    Log.e(TAG, "Not isEmail number");
                }
            }
        });

        button14 = findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sInput = "01011007001";
                if(Stringutils.isNumeric(sInput)){
                    Log.e(TAG, "isNumeric number");
                }else{
                    Log.e(TAG, "Not isNumeric number");
                }
            }
        });
    }

}