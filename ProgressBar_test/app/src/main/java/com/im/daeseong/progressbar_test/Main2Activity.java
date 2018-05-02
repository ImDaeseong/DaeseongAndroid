package com.im.daeseong.progressbar_test;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Main2Activity extends AppCompatActivity {

    private ProgressBar pb1;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pb1 = (ProgressBar)findViewById(R.id.pb1);

        new Thread()
        {
            public void run()
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animator = ValueAnimator.ofInt(0, 100);
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int progress = (int) animation.getAnimatedValue();
                                pb1.setProgress(progress);
                            }
                        });
                        animator.setRepeatCount(ValueAnimator.INFINITE);
                        animator.setDuration(5000);
                        animator.start();
                    }
                });
            }
        }.start();

    }
}
