package com.im.daeseong.swipe_test;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class Swipe2Activity extends AppCompatActivity {

    private static final String TAG = Swipe2Activity.class.getSimpleName();

    private GestureDetectorCompat gestureDetectorCompat;

    private static final int SWIPE_THRESHOLD = 30;
    private static final int SWIPE_VELOCITY_THRESHOLD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe2);

        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetectorCompat.onTouchEvent(event);
        //return super.onTouchEvent(event);
    }

    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
            //return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            boolean result = false;

            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        Log.e(TAG, " onSwipeRight ");
                    } else {
                        Log.e(TAG, " onSwipeLeft ");
                    }
                    result = true;
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY < 0) {
                        Log.e(TAG, "onSwipeUp ");
                    } else {
                        Log.e(TAG, "onSwipeDown ");
                    }
                    result = true;
                }
            }
            return result;
            //return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
