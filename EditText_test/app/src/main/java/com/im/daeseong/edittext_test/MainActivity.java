package com.im.daeseong.edittext_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private ExTextInputLayout et5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et1.getBackground().setColorFilter(Color.parseColor("#EDEDED"), PorterDuff.Mode.SRC_IN);
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

        et4 = (EditText) findViewById(R.id.et4);
        et4.setText("");
        et4.setBackground(getResources().getDrawable(R.drawable.edittext_select));
        et4.setTextColor(Color.BLACK);
        et4.setHintTextColor(Color.parseColor("#AFAFAF"));
        et4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                changeBackground(b, view);
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
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


        et5 = (ExTextInputLayout) findViewById(R.id.et5);
        et5.getEditInstance().setText("");
        et5.getEditInstance().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void changeBackground(boolean bSelect, View view){

        EditText et = (EditText) view;

        if(bSelect){

            et.setBackground(getResources().getDrawable(R.drawable.edittext_select));
        }else {

            et.setBackground(getResources().getDrawable(R.drawable.edittext_select_normal));
        }
    }

}
