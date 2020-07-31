package com.daeseong.imageviewgradient_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;

public class ImageViewEx5 extends androidx.appcompat.widget.AppCompatImageView {

    private Paint paint;
    private Drawable drawable;
    private int nHeight, nWidth;

    private boolean bfitXY;
    private String sText;

    public ImageViewEx5(Context context) {
        super(context);
        init();
    }

    public ImageViewEx5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewEx5(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){

        paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setFlags(TextPaint.ANTI_ALIAS_FLAG);
        paint.setTextSize(getResources().getDisplayMetrics().density * 10);
        sText = "이미지에 텍스트 추가";

        bfitXY = true;

        if(bfitXY) {
            setScaleType(ScaleType.FIT_XY);
        }
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

        drawable = getDrawable();
        drawable.draw(canvas);
        //int height = drawable.getIntrinsicHeight();
        //int width = drawable.getIntrinsicWidth();

        if(bfitXY) {

            canvas.drawText(sText, 17, getHeight() - 15, paint);
        }else {

            canvas.drawText(sText, 17, nHeight - 15, paint);
        }
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

    public void setText(String sText){
        this.sText = sText;
        invalidate();
    }
}
