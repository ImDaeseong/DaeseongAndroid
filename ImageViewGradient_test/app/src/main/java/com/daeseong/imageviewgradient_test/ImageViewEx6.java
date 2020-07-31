package com.daeseong.imageviewgradient_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class ImageViewEx6 extends androidx.appcompat.widget.AppCompatImageView {

    private Paint paint;
    private Path path;
    private RectF rect;
    private int nHeight, nWidth;
    private float fCornerRadius = 20f;

    public ImageViewEx6(Context context) {
        super(context);
        init();
    }

    public ImageViewEx6(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewEx6(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        setScaleType(ScaleType.FIT_XY);
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        nWidth = w;
        nHeight = h;

        //round border
        rect = new RectF(0, 0, nWidth, nHeight);
        path.addRoundRect(rect, fCornerRadius, fCornerRadius, Path.Direction.CCW);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
}
