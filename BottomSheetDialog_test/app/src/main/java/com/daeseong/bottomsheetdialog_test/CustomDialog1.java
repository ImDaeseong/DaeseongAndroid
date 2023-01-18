package com.daeseong.bottomsheetdialog_test;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;

public class CustomDialog1 extends Dialog {

    private static final String TAG = CustomDialog1.class.getSimpleName();

    private Context context;
    private Button btn1, btn2, btn3;

    public CustomDialog1(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout1);

        //다이얼로그 영역밖 터치, 백키 입력시 dismiss 막음
        setCancelable(false);

        //다이얼로그 영역밖 터치만
        setCanceledOnTouchOutside(false);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog2();
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog2();
            }
        });

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog2();
            }
        });
    }

    private void showCustomDialog2() {

        CustomDialog2 customDialog2 = new CustomDialog2(context);
        customDialog2.show();
        customDialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        customDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog2.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        customDialog2.getWindow().setGravity(Gravity.BOTTOM);
    }
}
