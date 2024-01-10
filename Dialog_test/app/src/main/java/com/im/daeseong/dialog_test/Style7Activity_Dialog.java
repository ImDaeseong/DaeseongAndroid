package com.im.daeseong.dialog_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class Style7Activity_Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_style7__dialog);

        setFinishOnTouchOutside(false);  //다른 영역 클릭 방지

        changeDisplay();
    }

    private void changeDisplay() {

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().getAttributes().windowAnimations = R.style.BottomPopupSlideTheme;
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public void btnOK_Click(View v){
        finish();
    }
}
