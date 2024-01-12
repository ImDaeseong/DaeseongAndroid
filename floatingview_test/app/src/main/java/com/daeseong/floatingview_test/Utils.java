package com.daeseong.floatingview_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

public class Utils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue / scale + 0.5F);
    }

    public static void slideingView(View view){

        float currentTranslationX = view.getTranslationX();

        float initialTranslationX = currentTranslationX + 1000f;
        float finalTranslationX = currentTranslationX;

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", initialTranslationX, finalTranslationX);
        animator1.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator1);
        animatorSet.start();
    }
}
