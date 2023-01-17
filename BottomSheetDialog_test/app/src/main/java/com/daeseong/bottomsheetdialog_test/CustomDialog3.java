package com.daeseong.bottomsheetdialog_test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;

public class CustomDialog3 extends Dialog {

    private static final String TAG = CustomDialog3.class.getSimpleName();

    private Context context;

    private View cL1;
    private Button btn1_1, btn2_1, btn3_1;

    private View cL2;
    private EditText et1, et2;
    private Button btn1;

    private boolean bClick = false;

    public CustomDialog3(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    private void setVisibleView(){

        if(bClick){

            cL1.setVisibility(View.GONE);
            cL2.setVisibility(View.VISIBLE);

        }else {

            cL1.setVisibility(View.VISIBLE);
            cL2.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout3);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

        cL1 = findViewById(R.id.cL1);
        cL2 = findViewById(R.id.cL2);

        setVisibleView();

        btn1_1 = findViewById(R.id.btn1_1);
        btn1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bClick = !bClick;
                setVisibleView();
            }
        });

        btn2_1 = findViewById(R.id.btn2_1);
        btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bClick = !bClick;
                setVisibleView();
            }
        });

        btn3_1 = findViewById(R.id.btn3_1);
        btn3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bClick = !bClick;
                setVisibleView();
            }
        });


        et1 = (EditText) findViewById(R.id.et1);
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

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

    }
}
