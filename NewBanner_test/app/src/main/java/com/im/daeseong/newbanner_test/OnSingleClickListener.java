package com.im.daeseong.newbanner_test;

import android.view.View;

public abstract class OnSingleClickListener implements View.OnClickListener{
    private long lastTime = 0;
    private long delayTime = 500;

    public OnSingleClickListener(){
    }

    public OnSingleClickListener(long delayTime){
        this.delayTime = delayTime;
    }

    @Override
    public void onClick(View view) {
        if( (System.currentTimeMillis() - lastTime) < delayTime ){
            return;
        }
        onSingleClick(view);
        lastTime = System.currentTimeMillis();
    }

    public abstract void onSingleClick(View view);
}

