package com.im.daeseong.marquee_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

import android.os.Handler;
import java.util.TimerTask;

public class MarqueeTask extends TimerTask {

    private Handler handler = null;
    private TextView tv;
    private boolean bFlag = false;

    public MarqueeTask(TextView tv){
        this.tv = tv;
        handler = new Handler();
    }

    public void run() {
        handler.post(new Runnable() {
            public void run() {
                marquee(tv, bFlag);
            }});
    }

    private void marquee(View view, boolean bFlag){
        if(bFlag) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f, -1000f);
            animator1.setDuration(500);

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f);
            animator2.setDuration(500);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(animator1, animator2);
            animatorSet.start();
        }else {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX",  0f, 1000f);
            animator1.setDuration(500);

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", -1000f, 0f);
            animator2.setDuration(500);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(animator1, animator2);
            animatorSet.start();
        }
        this.bFlag = !bFlag;
    }

}
