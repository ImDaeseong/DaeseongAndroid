package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Animate3Activity extends AppCompatActivity {

    private static final String TAG = Animate3Activity.class.getSimpleName();

    private Button button1, button2, button3;
    private int nWidth, nHeight;

    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate3);

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels;
        nHeight = displayMetrics.heightPixels;

        image1 = (ImageView)findViewById(R.id.image1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation1(image1);
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shakeAnimate1(Animate3Activity.this, image1);

            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shakeAnimate2(image1, 20, 5);
            }
        });
    }

    private void runAnimation1(ImageView imageView) {
        float offset = dip2px(imageView.getContext(), 20);
        ObjectAnimator right1 = ObjectAnimator.ofFloat(imageView, "translationX", offset);
        ObjectAnimator left1 = ObjectAnimator.ofFloat(imageView, "translationX", offset = offset * -0.7f);
        ObjectAnimator right2 = ObjectAnimator.ofFloat(imageView, "translationX", offset = offset * -0.7f);
        ObjectAnimator left2 = ObjectAnimator.ofFloat(imageView, "translationX", offset = offset * -0.7f);
        ObjectAnimator right3 = ObjectAnimator.ofFloat(imageView, "translationX", offset = offset * -0.7f);
        ObjectAnimator left3 = ObjectAnimator.ofFloat(imageView, "translationX", offset = offset * -0.7f);
        ObjectAnimator fixed = ObjectAnimator.ofFloat(imageView, "translationX", 0);
        AnimatorSet shake = new AnimatorSet();
        shake.play(right1).before(left1);
        shake.play(left1).before(right2);
        shake.play(right2).before(left2);
        shake.play(left2).before(right3);
        shake.play(right3).before(left3);
        shake.play(left3).before(fixed);
        shake.setDuration(130);
        shake.start();
    }

    private static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static void shakeAnimate1(Context context, View v){

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake);
        animation.setInterpolator(new CycleInterpolator(2));
        v.startAnimation(animation);
    }

    public static void shakeAnimate2(View v, int duration, int offset) {
        Animation anim = new TranslateAnimation(-offset,offset,0,0);
        anim.setDuration(duration);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(5);
        v.startAnimation(anim);
    }


}
