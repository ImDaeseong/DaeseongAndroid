package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class Animate1Activity extends AppCompatActivity {

    private static final String TAG = Animate1Activity.class.getSimpleName();

    private Button button1, button2, button3;

    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate1);

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

                runAnimation2(image1);
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation3(image1);
            }
        });

    }

    private void runAnimation1(ImageView imageView) {

        //Y축으로 회전
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 720f);
        //objectAnimator.setInterpolator(new LinearInterpolator());//일정한 속도
        //objectAnimator.setInterpolator(new AccelerateInterpolator());//점점 빠르게 속도
        //objectAnimator.setInterpolator(new DecelerateInterpolator());//점점 느리게 속도
        objectAnimator.setDuration(2000);;//2초동안 실행
        objectAnimator.start();
    }

    private void runAnimation2(ImageView imageView) {

        //x축으로 회전
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotationX", 0f, 720f);
        //objectAnimator.setInterpolator(new LinearInterpolator());//일정한 속도
        //objectAnimator.setInterpolator(new AccelerateInterpolator());//점점 빠르게 속도
        //objectAnimator.setInterpolator(new DecelerateInterpolator());//점점 느리게 속도
        objectAnimator.setDuration(2000);;//2초동안 실행
        objectAnimator.start();
    }

    private void runAnimation3(ImageView imageView) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 720f);
        objectAnimator.setDuration(2000);;//2초동안 실행

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                //완료시 이미지 교체
                image1.setImageResource(R.drawable.img2);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        objectAnimator.start();
    }

}
