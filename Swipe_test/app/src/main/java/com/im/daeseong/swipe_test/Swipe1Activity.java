package com.im.daeseong.swipe_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Swipe1Activity extends AppCompatActivity {

    private static final String TAG = Swipe1Activity.class.getSimpleName();

    private ImageView iv1;

    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe1);

        iv1 = (ImageView)findViewById(R.id.iv1);
        iv1.setOnTouchListener(new MySwipeListener(this));


        //x,y 사이즈 변경, 알파값 변경, 회전
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(iv1, "scaleX", 0.1f, 0.5f, 1f),
                ObjectAnimator.ofFloat(iv1, "scaleY", 0.1f, 0.5f, 1f),
                ObjectAnimator.ofFloat(iv1, "alpha", 0f, 1f),
                ObjectAnimator.ofFloat(iv1, "rotation", 1080, 720, 360, 0));
        animatorSet.setDuration(1000);
        animatorSet.start();


        //알파값에 따른 변화
        /*
        objectAnimator = ObjectAnimator.ofFloat(iv1, "alpha", 1, 0.2f);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();
        */


        //커졌다 작아졌다 반복
        /*
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv1, "scaleY", 1f, 0.8f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv1, "scaleX", 1f, 0.8f, 1f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(iv1, "alpha", 1f, 0f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(iv1, "alpha", 0f, 1f);
        scaleX.setRepeatCount(-1);
        scaleY.setRepeatCount(-1);
        fadeOut.setRepeatCount(-1);
        fadeIn.setRepeatCount(-1);
        animatorSet.playTogether(scaleY, fadeIn, scaleX, fadeOut );//animatorSet.playTogether(scaleY, scaleX);
        animatorSet.setDuration(1000);
        animatorSet.start();
        */


        //좌우 반복
        /*
        objectAnimator = ObjectAnimator.ofFloat(iv1, "TranslationX", -10, 10);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
        */


        //위 아래 반복
        /*
        objectAnimator = ObjectAnimator.ofFloat(iv1, "TranslationY", -10, 10);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
        */


        //360 도 회전후 원래대로
        /*
        objectAnimator = ObjectAnimator.ofFloat(iv1, "rotation", 0, 360);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
        */


        //360 도 회전 반복
        /*
        objectAnimator = ObjectAnimator.ofFloat(iv1,"rotation",360);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
        */


        //위에서 아래로
        /*
        objectAnimator = ObjectAnimator.ofFloat(iv1, iv1.TRANSLATION_Y, -500, -200, -50, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        */


        //아래에서 위로
        /*
        objectAnimator = ObjectAnimator.ofFloat(iv1, iv1.TRANSLATION_Y, 500, 200, 50, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        */

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator = null;
        }
    }

    private class MySwipeListener extends SwipeListener{

        public MySwipeListener(Context context) {
            super(context);
        }

        @Override
        public boolean swipeUp() {
            Log.e(TAG, "swipeUp");
            return true;
        }

        @Override
        public boolean swipeDown() {
            Log.e(TAG, "swipeDown");
            return true;
        }

        @Override
        public boolean swipeLeft() {
            Log.e(TAG, "swipeLeft");
            return true;
        }

        @Override
        public boolean swipeRight() {
            Log.e(TAG, "swipeRight");
            return true;
        }
    }

}
