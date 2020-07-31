package com.daeseong.imageviewgradient_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class ImageViewEx4 extends androidx.appcompat.widget.AppCompatImageView  {

    private float fCornerRadius = 15;

    public ImageViewEx4(Context context) {
        super(context);
    }

    public ImageViewEx4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewEx4(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //round border
        Drawable myDrawable = getDrawable();

        if (myDrawable != null && myDrawable instanceof BitmapDrawable && fCornerRadius > 0) {

            Paint paint = ((BitmapDrawable) myDrawable).getPaint();
            final int color = 0xff000000;
            Rect bitmapBounds = myDrawable.getBounds();
            final RectF rectF = new RectF(bitmapBounds);
            int saveCount = canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG);
            getImageMatrix().mapRect(rectF);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, fCornerRadius, fCornerRadius, paint);

            Xfermode oldMode = paint.getXfermode();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            super.onDraw(canvas);

            paint.setXfermode(oldMode);
            canvas.restoreToCount(saveCount);

        } else {

            super.onDraw(canvas);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    public void setCornerRadius(float fCornerRadius) {
        this.fCornerRadius = fCornerRadius;
        invalidate();
        //invalidate();//좀 더 빠르게 화면을 redraw
        //postInvalidate();//좀 덜 빠르게 화면을 refresh
    }
}
