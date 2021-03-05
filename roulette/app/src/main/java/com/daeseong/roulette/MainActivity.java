package com.daeseong.roulette;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView imageview1;

    int nRouletteCount = 6;
    float startDegree = 0f;
    float endDegree = 0f;
    float divDegree = 0f;
    float repeatDegree = 360f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview1 = (ImageView)findViewById(R.id.imageview1);
        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startDegree = divDegree;
                Random rand = new Random();
                int degree_rand = rand.nextInt(360);
                endDegree = startDegree + (360 * nRouletteCount) + degree_rand;
                divDegree = ((endDegree) % 360);

                //Log.e(TAG, "degree_rand:" + degree_rand);
                //Log.e(TAG, "startDegree:" + startDegree);
                //Log.e(TAG, "endDegree:" + endDegree);
                //Log.e(TAG, "divDegree:" + divDegree);

                int nResult = getResult(divDegree);
                if(nResult == 1){
                    repeatDegree = 360f;
                }else if(nResult == 2){
                    repeatDegree = 300f;
                }else if(nResult == 3){
                    repeatDegree = 240f;
                }else if(nResult == 4){
                    repeatDegree = 180f;
                }else if(nResult == 5){
                    repeatDegree = 120f;
                }else if(nResult == 6){
                    repeatDegree = 60f;
                }
                //Log.e(TAG, "nResult:" + nResult);

                ObjectAnimator object1 = ObjectAnimator.ofFloat(imageview1, "rotation", startDegree, endDegree);
                object1.setInterpolator(new LinearInterpolator());//일정하게
                object1.setDuration(1000);
                object1.start();

                ObjectAnimator object2 = ObjectAnimator.ofFloat(imageview1, "rotation", 0f, repeatDegree);
                object2.setInterpolator(new LinearInterpolator());//일정하게
                object2.setDuration(1000);
                object2.start();

                /*
                ObjectAnimator object1 = ObjectAnimator.ofFloat(imageview1, "rotation", startDegree, endDegree);
                ObjectAnimator object2 = ObjectAnimator.ofFloat(imageview1, "rotation", 0f, repeatDegree);
                AnimatorSet animSet = new AnimatorSet();
                animSet.playTogether(object1, object2);
                animSet.setDuration(1000);
                animSet.setInterpolator(new AccelerateDecelerateInterpolator());//빨리 돌다가 점덤 천천히
                animSet.start();
                */
            }
        });
    }

    private int getResult(float angle){

        /*
        4개 일 경우 90도씩 1:0 ~ 90, 2:90 ~ 180, 3:180 ~ 270, 4:270 ~ 360
        6개 일 경우 60도씩 1:0 ~ 60, 2:60 ~ 120, 3:120 ~ 180, 4:180 ~ 240, 5:240 ~ 300, 6:300 ~ 360
        */

        //테스트 이미지는 30도씩 왼쪽으로 틀어져있음 그래서 30도씩 왼쪽으로 30도 이동
        //1:330 ~ 30, 2:30 ~ 90, 3:90 ~ 150, 4:150 ~ 210, 5:210 ~ 270, 6:270 ~ 330

        int nResult = 0;

        if( angle > 330 || angle <= 30){

            nResult = 1;
        } else if(angle > 30 && angle <= 90) {

            nResult = 2;
        } else if(angle > 90 && angle <= 150) {

            nResult = 3;
        } else if(angle > 150 && angle <= 210) {

            nResult = 4;
        } else if(angle > 210 && angle <= 270) {

            nResult = 5;
        } else if(angle > 270 && angle <= 330) {

            nResult = 6;
        }
        return nResult;
    }

}
