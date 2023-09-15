package com.im.daeseong.mainui_test;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private boolean noScroll = false;

    public CustomViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public CustomViewPager(Context context){
        super(context);
    }

    public void setNoScroll(boolean noScroll){
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(noScroll)
            return false;
        else
            return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(ev);
    }
}
