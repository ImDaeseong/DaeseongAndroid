package com.daeseong.imageviewgradient_test;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class ImageViewEx8 extends androidx.appcompat.widget.AppCompatImageView {

    private static final String TAG = ImageViewEx8.class.getSimpleName();

    private Paint paint;
    private int nHeight, nWidth;

    public ImageViewEx8(Context context) {
        super(context);
        init();
    }

    public ImageViewEx8(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewEx8(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setAntiAlias(true);
        //paint.setAlpha(204);//255 * 0.8 =204 80%
        paint.setAlpha(51);//255 * 0.2 =51 20%
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        nWidth = getMeasuredWidth();
        nHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //절반만 투명처리
        canvas.drawRect(0, nHeight / 2, nWidth, nHeight, paint);
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
}
