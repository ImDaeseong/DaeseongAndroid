package com.im.daeseong.dialog_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class Style2Activity_Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_style2__dialog);

        setFinishOnTouchOutside(false);  //다른 영역 클릭 방지
    }

    public void btnOK_Click(View v){
        Log.e("TAG", "btnOK");
        finish();
    }
}
