package com.daeseong.imageviewgradient_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

public class ImageViewEx1 extends androidx.appcompat.widget.AppCompatImageView {

    private Paint paint;
    private LinearGradient gradient;
    private int nHeight, nWidth;
    private int nColor = Color.argb(204, 0, 0, 0);//255 * 0.8 =204 80%
    //private int nColor = Color.argb(51, 0, 0, 0);//255 * 0.2 =51 20%

    public ImageViewEx1(Context context) {
        super(context);
        init();
    }

    public ImageViewEx1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewEx1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(gradient == null){

            //왼쪽
            //gradient = new LinearGradient(0, nHeight, nWidth / 2, nHeight, nColor, Color.TRANSPARENT, Shader.TileMode.CLAMP);

            //오른쪽
            //gradient = new LinearGradient(nWidth /2, nHeight, nWidth, nHeight, Color.TRANSPARENT, nColor, Shader.TileMode.CLAMP);

            //위쪽
            //gradient = new LinearGradient(nWidth, 0, nWidth, nHeight /2, nColor, Color.TRANSPARENT, Shader.TileMode.CLAMP);

            //아래쪽
            gradient = new LinearGradient(nWidth, nHeight / 2, nWidth, nHeight, Color.TRANSPARENT, nColor, Shader.TileMode.CLAMP);

            paint.setShader(gradient);
        }
        canvas.drawPaint(paint);
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
