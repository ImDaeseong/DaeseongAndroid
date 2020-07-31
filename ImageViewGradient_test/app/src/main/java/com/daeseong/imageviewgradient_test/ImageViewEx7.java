package com.daeseong.imageviewgradient_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class ImageViewEx7 extends androidx.appcompat.widget.AppCompatImageView {

    private Paint paint;
    private Rect rect;

    //빨간색 border
    private int nBorderColor = Color.argb(255, 255, 0, 0);

    public ImageViewEx7(Context context) {
        super(context);
        init();
    }

    public ImageViewEx7(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewEx7(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rect = canvas.getClipBounds();
        rect.bottom--;
        rect.right--;
        paint.setColor(nBorderColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
    }
}
