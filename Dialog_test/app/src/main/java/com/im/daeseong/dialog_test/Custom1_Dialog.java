package com.im.daeseong.dialog_test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Custom1_Dialog extends Dialog {

    private Button btncancel_dialog, btnclose_dialog;

    private View.OnClickListener mcloseListener;

    public Custom1_Dialog(Context context, View.OnClickListener closeListener){
        super(context);
        mcloseListener = closeListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom1_dialog);

        setCancelable(false);
        setCanceledOnTouchOutside(true);    // dialog 밖에 터치했을 때 사라지기

        btncancel_dialog = (Button)findViewById(R.id.btncancel_dialog);
        btnclose_dialog =(Button)findViewById(R.id.btnclose_dialog);

        btncancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnclose_dialog.setOnClickListener(mcloseListener);
    }

}
