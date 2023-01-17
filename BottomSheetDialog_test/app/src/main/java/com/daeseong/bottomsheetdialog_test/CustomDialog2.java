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

public class CustomDialog2 extends Dialog {

    private static final String TAG = CustomDialog2.class.getSimpleName();

    private Context context;
    private EditText et1, et2;
    private Button btn1;

    public CustomDialog2(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout2);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

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

                String sValue1 = et1.getText().toString();
                String sValue2 = et2.getText().toString();
                Log.e(TAG, sValue1 + "  " + sValue2);

                dismiss();
            }
        });

    }
}
