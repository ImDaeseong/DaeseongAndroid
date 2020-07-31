package com.daeseong.imageviewgradient_test;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class ImageViewEx3 extends androidx.appcompat.widget.AppCompatImageView {

    private int nHeight, nWidth;
    private float fRotation = 90;

    public ImageViewEx3(Context context) {
        super(context);
    }

    public ImageViewEx3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewEx3(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //90 rotate
        canvas.rotate(fRotation, nWidth / 2, nHeight / 2);

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        nWidth = w;
        nHeight = h;
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    public void setRotation(float fRotation) {
        this.fRotation = fRotation;
        invalidate();
        //invalidate();//좀 더 빠르게 화면을 redraw
        //postInvalidate();//좀 덜 빠르게 화면을 refresh
    }
}
