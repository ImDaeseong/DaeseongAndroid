package com.daeseong.imageviewgradient_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;

public class ImageViewEx2 extends androidx.appcompat.widget.AppCompatImageView {

    private Paint paint;
    private Shader shader;
    private int nHeight, nWidth;
    private int[] nColors =  new int[]{Color.RED, Color.TRANSPARENT};

    public ImageViewEx2(Context context) {
        super(context);
        init();
    }

    public ImageViewEx2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewEx2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //가운데 좌표
        int nX = nWidth/2;
        int nY = nHeight/2;

        //표시할 영역
        int nRadius = nWidth/4;

        if(shader == null){
            shader = new RadialGradient(nX, nY, nRadius, nColors, null, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            canvas.drawCircle(nX, nY, nRadius, paint);
        }
        canvas.drawCircle(nX, nY, nRadius, paint);
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
