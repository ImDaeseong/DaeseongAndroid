package com.im.daeseong.dialog_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Style4Activity_Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_style4__dialog);

        setFinishOnTouchOutside(false);  //다른 영역 클릭 방지
    }

    public void btnOK_Click(View v){
        finish();
    }
}
