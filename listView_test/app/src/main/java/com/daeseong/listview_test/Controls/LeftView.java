package com.daeseong.listview_test.Controls;

import static android.view.Gravity.CENTER;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftView extends ViewGroup {

    private static final String TAG = LeftView.class.getSimpleName();

    private TextView tv1;

    public LeftView(Context context, String sText) {
        super(context);

        setBackgroundColor(Color.GRAY);

        tv1 = new TextView(context);
        tv1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tv1.setText(sText);
        tv1.setTextSize(30);
        tv1.setTextColor(Color.BLACK);
        tv1.setGravity(CENTER);
        tv1.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, 150);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        tv1.layout(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tv1.draw(canvas);
    }

    public void setSelectColor(){
        setBackgroundColor(Color.WHITE);
    }
}

