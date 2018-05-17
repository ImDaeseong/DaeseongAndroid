package com.im.daeseong.swipe_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class AnimatorUtil {

    //x,y 사이즈 변경, 알파값 변경, 회전
    public static void Animato1(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.5f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.5f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
                ObjectAnimator.ofFloat(view, "rotation", 1080, 720, 360, 0));
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    //알파값에 따른 변화
    public static void Animato2(View view){

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1, 0.2f);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();
    }

    //커졌다 작아졌다 반복
    public static void Animato3(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f, 1f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        scaleX.setRepeatCount(-1);
        scaleY.setRepeatCount(-1);
        fadeOut.setRepeatCount(-1);
        fadeIn.setRepeatCount(-1);
        animatorSet.playTogether(scaleY, fadeIn, scaleX, fadeOut );//animatorSet.playTogether(scaleY, scaleX);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    //좌우 반복
    public static void Animato4(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "TranslationX", -10, 10);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
    }

    //위 아래 반복
    public static void Animato5(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "TranslationY", -10, 10);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
    }

    //360 도 회전후 원래대로
    public static void Animato6(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
    }

    //360 도 회전 반복
    public static void Animato7(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"rotation",360);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    //위에서 아래로
    public static void Animato8(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, view.TRANSLATION_Y, -1000, -500, -200, -50, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    //아래에서 위로
    public static void Animato9(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, view.TRANSLATION_Y, 1000, 500, 200, 50, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    //왼쪽으로 swipe
    public static void Animato10(View view){

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f, -1000f);
        animator1.setDuration(500);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f);
        animator2.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
        animatorSet.start();
    }

    //오른쪽으로 swipe
    public static void Animato11(View view){

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX",  0f, 1000f);
        animator1.setDuration(500);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", -1000f, 0f);
        animator2.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
        animatorSet.start();
    }

}
