package com.im.daeseong.animationutils_test;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main5Activity extends AppCompatActivity {

    private ImageView iv1;
    private boolean banimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        iv1 = (ImageView)findViewById(R.id.iv1);

        startanimate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Main5Activity.this, MainActivity.class);
                startActivity(intent);

                stopanimate();

                Main5Activity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 2000);
    }

    private void startanimate(){
        banimate = true;
        animateToAlpha(0.5f);
    }

    private void stopanimate(){
        banimate = false;
    }

    private void animateToAlpha(final float alpha){
        if (banimate) {
            ObjectAnimator anim = ObjectAnimator.ofFloat(iv1, "alpha", alpha);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (banimate) {
                        animateToAlpha(alpha == 0.5f ? 0.0f : 0.5f);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            anim.setDuration(300).start();
        }
    }



}
