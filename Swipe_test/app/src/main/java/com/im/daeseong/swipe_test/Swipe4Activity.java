package com.im.daeseong.swipe_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.support.v7.app.AppCompatActivity;
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

                //ObjectAnimator animator = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX",  1000, 0);
                ObjectAnimator animator = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX", swipeConstraintLayout.getWidth(), 0f, 0f);
                //ObjectAnimator animator = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX", swipeConstraintLayout.getWidth(), 0f, 0f,-1000f);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animator);
                animatorSet.setDuration(1000);
                animatorSet.start();

            }

            @Override
            public void swipeRight() {
                Log.e(TAG, "swipeRight");

                //ObjectAnimator animator =  ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX", -1000, 0);
                ObjectAnimator animator = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX",  -swipeConstraintLayout.getWidth(), 0f, 0f);
                //ObjectAnimator animator = ObjectAnimator.ofFloat(swipeConstraintLayout, "translationX",  -swipeConstraintLayout.getWidth(), 0f, 0f,1000f);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animator);
                animatorSet.setDuration(1000);
                animatorSet.start();

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
    }

}
