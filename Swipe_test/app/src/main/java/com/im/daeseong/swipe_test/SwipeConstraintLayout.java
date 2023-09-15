package com.im.daeseong.swipe_test;

import android.content.Context;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;//import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeConstraintLayout extends ConstraintLayout {

    private static final int SWIPE_THRESHOLD = 30;
    private static final int SWIPE_VELOCITY_THRESHOLD = 1;

    private GestureDetector gestureDetector;
    private OnSwipeConstraintListener swipeConstraintListener;

    public SwipeConstraintLayout(@NonNull Context context){
        super(context);
        init();
    }

    public SwipeConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                boolean result = false;

                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();

                if (Math.abs(diffX) > Math.abs(diffY)) {

                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            if(swipeConstraintListener != null){
                                swipeConstraintListener.swipeRight();
                            }
                        } else {
                            if(swipeConstraintListener != null){
                                swipeConstraintListener.swipeLeft();
                            }
                        }
                        result = true;
                    }
                } else {

                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY < 0) {
                            if(swipeConstraintListener != null){
                                swipeConstraintListener.swipeUp();
                            }
                        } else {
                            if(swipeConstraintListener != null){
                                swipeConstraintListener.swipeDown();
                            }
                        }
                        result = true;
                    }
                }
                return result;
                //return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    public void setOnSwipeListener(OnSwipeConstraintListener swipeConstraintListener) {
        this.swipeConstraintListener = swipeConstraintListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
        //return super.onTouchEvent(event);
    }

    public interface OnSwipeConstraintListener{
        void swipeLeft();
        void swipeRight();
        void swipeUp();
        void swipeDown();
    }

}
