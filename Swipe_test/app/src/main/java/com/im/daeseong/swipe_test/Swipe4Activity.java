package com.im.daeseong.swipe_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Swipe4Activity extends AppCompatActivity {

    private static final String TAG = Swipe4Activity.class.getSimpleName();

    private SwipeConstraintLayout swipeConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe4);

        swipeConstraintLayout = (SwipeConstraintLayout)findViewById(R.id.swCL);
        swipeConstraintLayout.setOnSwipeListener(new SwipeConstraintLayout.OnSwipeConstraintListener() {
            @Override
            public void swipeLeft() {
                Log.e(TAG, "swipeLeft");

                AnimatorUtil.Animato10(swipeConstraintLayout);

                /*
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX", 0f, -1000f);
                animator1.setDuration(500);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX", 1000f, 0f);
                animator2.setDuration(500);


                ObjectAnimator animator3 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -10f, 10f);
                animator3.setDuration(10);

                ObjectAnimator animator4 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -8f, 8f);
                animator4.setDuration(10);

                ObjectAnimator animator5 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -6f, 6f);
                animator5.setDuration(10);

                ObjectAnimator animator6 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -4f, 4f);
                animator6.setDuration(10);

                ObjectAnimator animator7 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -2f, 2f);
                animator7.setDuration(10);

                ObjectAnimator animator8 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -1f, 0f);
                animator8.setDuration(10);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animator1, animator2, animator3, animator4, animator5, animator6, animator7, animator8);
                animatorSet.start();
                */
            }

            @Override
            public void swipeRight() {
                Log.e(TAG, "swipeRight");

                AnimatorUtil.Animato11(swipeConstraintLayout);

                /*
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX",  0f, 1000f);
                animator1.setDuration(500);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX", -1000f, 0f);
                animator2.setDuration(500);


                ObjectAnimator animator3 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -10f, 10f);
                animator3.setDuration(10);

                ObjectAnimator animator4 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -8f, 8f);
                animator4.setDuration(10);

                ObjectAnimator animator5 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -6f, 6f);
                animator5.setDuration(10);

                ObjectAnimator animator6 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -4f, 4f);
                animator6.setDuration(10);

                ObjectAnimator animator7 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -2f, 2f);
                animator7.setDuration(10);

                ObjectAnimator animator8 = ObjectAnimator.ofFloat(swipeConstraintLayout, "TranslationX", -1f, 0f);
                animator8.setDuration(10);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animator1, animator2, animator3, animator4, animator5, animator6, animator7, animator8);
                animatorSet.start();
                */
            }

            @Override
            public void swipeUp() {
                Log.e(TAG, "swipeUp");
            }

            @Override
            public void swipeDown() {
                Log.e(TAG, "swipeDown");
            }
        });

        AnimatoTopBottom();
    }

    private void AnimatoTopBottom(){
        Log.e(TAG, "swipeTop-Bottom");

        AnimatorUtil.Animato8(swipeConstraintLayout);

        /*
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationY",  -1000, -500, -200, -50, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        */
    }


}
