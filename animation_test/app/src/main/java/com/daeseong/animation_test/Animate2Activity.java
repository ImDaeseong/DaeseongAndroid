package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class Animate2Activity extends AppCompatActivity {

    private static final String TAG = Animate2Activity.class.getSimpleName();

    private Button button1, button2, button3, button4;

    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate2);

        image1 = (ImageView)findViewById(R.id.image1);
        //image1.setVisibility(View.INVISIBLE);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation1(image1, false);
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation2(image1, true);
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation3(image1, 1);
            }
        });

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation4(image1);
            }
        });
    }

    private void runAnimation1(ImageView imageView, boolean bShow) {

        if(bShow) {

            //점점 보이게
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0, 1);
            objectAnimator.setDuration(2000);
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.start();

        }else {

            //점점 않보이게
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1, 0);
            objectAnimator.setDuration(2000);
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.start();
        }
    }

    private void runAnimation2(ImageView imageView, boolean bShow) {

        if(bShow){

            //점점 보이게
            //작은 사이즈에서 원래 사이즈 대로 커짐
            ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0, 1);
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.618f, 1);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.618f, 1);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(alpha, scaleX, scaleY);
            set.setInterpolator(new DecelerateInterpolator());
            set.setDuration(2000);
            set.start();

        }else {

            //점점 않보이게
            //원래 사이즈에서 작은 사이즈 작아짐
            ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1, 0);
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", imageView.getScaleX(), 0.618f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", imageView.getScaleY(), 0.618f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(alpha, scaleX, scaleY);
            set.setInterpolator(new DecelerateInterpolator());
            set.setDuration(2000);
            set.start();

        }
    }

    private void runAnimation3(ImageView imageView, int nType){

        if(nType == 1){

            //현재 위치에서 왼쪽으로 이동
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", imageView.getTranslationX(), -imageView.getRight());
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.setDuration(2000);
            objectAnimator.start();

        }else if(nType == 2){

            //현재 위치에서 오른쪽으로 이동
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", imageView.getTranslationX(), imageView.getRight());
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.setDuration(2000);
            objectAnimator.start();

        }else if(nType == 3){

            //현재 위치에서 아래쪽으로 이동
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", imageView.getTranslationX(), imageView.getRight());
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.setDuration(2000);
            objectAnimator.start();

        }else if(nType == 4){

            //현재 위치에서 위쪽으로 이동
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", imageView.getTranslationX(), -imageView.getRight());
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.setDuration(2000);
            objectAnimator.start();

        }
    }

    public void runAnimation4(ImageView imageView) {

        //점점 않보이게
        //작은 사이즈에서 점점 큰사이즈로

        Animation animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true); //애니메이션 종료후 원래 위치로 돌아가지 않고 종료 디폴트 setFillAfter(false); 는 원래 위치로 돌아감
        animation.setDuration(500);
        imageView.startAnimation(animation);
    }

}
