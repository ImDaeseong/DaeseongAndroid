package com.daeseong.countdownlabel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.Timer;
import java.util.TimerTask;

public class TextViewEx extends androidx.appcompat.widget.AppCompatTextView {

    private int nSaveCount = 0;
    private int nCount = 0;

    private Timer timer = new Timer();
    private AnimatorSet animatorSet = new AnimatorSet();

    public TextViewEx(Context context) {
        super(context);
    }

    public TextViewEx(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCount(int nCount){
        this.nSaveCount = nCount;
    }

    public void startTimer(){

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {

                        if(nCount > 0){

                            nCount--;
                            String sSecond = String.format("현재 페이지 %02d 초", nCount);
                            setText(sSecond);
                            viewblink(TextViewEx.this);
                        } else {

                            stopTimer();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    public void stopTimer(){

        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            animatorSet.end();
            animatorSet.cancel();
        }

        if (timer != null){
            timer.cancel();
        }

        setAlpha(1f);
        nCount = nSaveCount;
    }

    private void viewblink(View view){
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animatorSet.playTogether(fadeIn, fadeOut );
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
}
