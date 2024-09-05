package com.daeseong.horizontalscrollview_flingtest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HorizontalScrollView2Activity extends AppCompatActivity {

    private static final String TAG = HorizontalScrollView2Activity.class.getSimpleName();

    private LinearLayout llayout;

    private int nWidth, nHeight, nImgHeight;

    private int nImgCount = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view2);

        init();
    }

    private void init(){

        llayout = (LinearLayout) findViewById(R.id.llayout);

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels - dip2px(this, 29);
        nHeight = displayMetrics.heightPixels;
        nImgHeight = (int) ((float) ((320) * (float) ((float) nWidth / 960)));

        for (int i = 0; i < nImgCount; i++) {
            setImageView(i);
        }
    }

    private void setImageView(int nIndex) {

        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(nImgHeight * 2, nImgHeight);

        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(params);

        //마진 설정
        if (nIndex == 0){
            params.setMargins(0,0,0,0);
        }else {
            params.setMargins(10,0,0,0);
        }

        if (nIndex % 2 == 1) {
            imageView.setImageResource(R.drawable.a);
        } else {
            imageView.setImageResource(R.drawable.b);
        }
        llayout.addView(imageView);
    }

    private static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
