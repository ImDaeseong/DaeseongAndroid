package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public class Animate4Activity extends AppCompatActivity {

    private static final String TAG = Animate4Activity.class.getSimpleName();

    private View cL1;
    private Button button1, button2;

    private int nWidth, nHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate4);

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels;
        nHeight = displayMetrics.heightPixels;

        cL1 = (View) findViewById(R.id.cL1);
        cL1.setVisibility(View.INVISIBLE);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation1();
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation2();
            }
        });
    }

    private void runAnimation1() {

        //아래에서 위로 올라오면서 화면에 보여준다.

        float fheight = nHeight;//cL1.getMeasuredHeight();

        cL1.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cL1, "translationY", fheight, 0f);
        objectAnimator.setDuration(350);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }

    private void runAnimation2() {

        //현재 위치에서 아래로 내려가면서 숨긴다

        float fheight = nHeight;//cL1.getMeasuredHeight();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cL1, "translationY", 0f, fheight);
        objectAnimator.setDuration(350);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }
}
