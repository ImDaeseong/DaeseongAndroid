package com.im.daeseong.swipe_test;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SwipeListener extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener{

    private static final String TAG = SwipeListener.class.getSimpleName();

    private static final int SWIPE_THRESHOLD = 30;
    private static final int SWIPE_VELOCITY_THRESHOLD = 1;


    private GestureDetector gestureDetector;
    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }
    public void setGestureDetector(GestureDetector gestureDetector){
        this.gestureDetector = gestureDetector;
    }

    public boolean swipeLeft(){
        return false;
    }
    public boolean swipeRight(){
        return false;
    }
    public boolean swipeUp(){
        return false;
    }
    public boolean swipeDown(){
        return false;
    }

    public SwipeListener(Context context){
        super();
        gestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        boolean result = false;

        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();

        if (Math.abs(diffX) > Math.abs(diffY)) {

            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    swipeRight();
                } else {
                    swipeLeft();
                }
                result = true;
            }
        } else {

            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY < 0) {
                    swipeUp();
                } else {
                    swipeDown();
                }
                result = true;
            }
        }
        return result;
        //return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        gestureDetector.onTouchEvent(motionEvent);

        return false;
    }

}
