package com.im.daeseong.edittext_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected EditText et1;
    protected EditText et2;
    protected EditText et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        //et1.getBackground().setColorFilter(Color.parseColor("#EDEDED"), PorterDuff.Mode.SRC_IN);
        et1.setText("");
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et2 = (EditText) findViewById(R.id.et2);
        //et2.getBackground().setColorFilter(Color.parseColor("#EDEDED"), PorterDuff.Mode.SRC_IN);
        et2.setText("");
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et3 = (EditText) findViewById(R.id.et3);
        //et3.getBackground().setColorFilter(Color.parseColor("#EDEDED"), PorterDuff.Mode.SRC_IN);
        et3.setText("");
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
}
