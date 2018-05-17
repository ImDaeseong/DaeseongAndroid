package com.im.daeseong.swipe_test;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Swipe1Activity extends AppCompatActivity {

    private static final String TAG = Swipe1Activity.class.getSimpleName();

    private ImageView iv1;

    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe1);

        iv1 = (ImageView)findViewById(R.id.iv1);
        iv1.setOnTouchListener(new MySwipeListener(this));


        //x,y 사이즈 변경, 알파값 변경, 회전
        AnimatorUtil.Animato1(iv1);

        //알파값에 따른 변화
        //AnimatorUtil.Animato2(iv1);

        //커졌다 작아졌다 반복
        //AnimatorUtil.Animato3(iv1);

        //좌우 반복
        //AnimatorUtil.Animato4(iv1);

        //위 아래 반복
        //AnimatorUtil.Animato5(iv1);

        //360 도 회전후 원래대로
        //AnimatorUtil.Animato6(iv1);

        //360 도 회전 반복
        //AnimatorUtil.Animato7(iv1);

        //위에서 아래로
        //AnimatorUtil.Animato8(iv1);

        //아래에서 위로
        //AnimatorUtil.Animato9(iv1);

        //왼쪽으로 swipe
        //AnimatorUtil.Animato10(iv1);

        //오른쪽으로 swipe
        //AnimatorUtil.Animato11(iv1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator = null;
        }
    }

    private class MySwipeListener extends SwipeListener{

        public MySwipeListener(Context context) {
            super(context);
        }

        @Override
        public boolean swipeUp() {
            Log.e(TAG, "swipeUp");
            return true;
        }

        @Override
        public boolean swipeDown() {
            Log.e(TAG, "swipeDown");
            return true;
        }

        @Override
        public boolean swipeLeft() {
            Log.e(TAG, "swipeLeft");
            return true;
        }

        @Override
        public boolean swipeRight() {
            Log.e(TAG, "swipeRight");
            return true;
        }
    }

}
