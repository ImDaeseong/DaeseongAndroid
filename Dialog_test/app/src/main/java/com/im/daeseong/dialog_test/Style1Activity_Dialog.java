package com.im.daeseong.dialog_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Style1Activity_Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_style1__dialog);

        setFinishOnTouchOutside(false);  //다른 영역 클릭 방지
    }

    public void btnOK_Click(View v){
        finish();
    }

    public void btnCancel_Click(View v){
        finish();
    }

}
