package com.daeseong.bottomsheetdialog_test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {

    private static final String TAG = CustomDialog.class.getSimpleName();

    private Context context;

    private TextView tv1;
    private Button btn1, btn2;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        //다이얼로그 영역밖 터치, 백키 입력시 dismiss 막음
        setCancelable(false);

        //다이얼로그 영역밖 터치만
        setCanceledOnTouchOutside(false);

        tv1 = findViewById(R.id.tv1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, tv1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });
    }
}
