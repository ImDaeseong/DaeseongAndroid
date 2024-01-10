package com.im.daeseong.dialog_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class Style5Activity_Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_style5__dialog);

        setFinishOnTouchOutside(false);  //다른 영역 클릭 방지

        changeDisplay();
    }

    private void changeDisplay() {

        try {

            Display display = getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            int screenLength = size.y;
            int screenwidth = size.x;

            if (screenLength > 0 && screenwidth > 0) {

                int ViewWidth = (screenwidth - Utils.dip2px(this, 48));
                getWindow().setLayout(ViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

                WindowManager.LayoutParams WMLP = getWindow().getAttributes();
                WMLP.gravity = Gravity.CENTER;
                WMLP.x = 0;
                WMLP.y = 0;
                getWindow().setAttributes(WMLP);
            }

        } catch (Exception ex) {
        }
    }

    public void btnOK_Click(View v){
        finish();
    }
}
