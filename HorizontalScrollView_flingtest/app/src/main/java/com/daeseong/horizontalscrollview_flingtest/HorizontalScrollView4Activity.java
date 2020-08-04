package com.daeseong.horizontalscrollview_flingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class HorizontalScrollView4Activity extends AppCompatActivity {

    private static final String TAG = HorizontalScrollView4Activity.class.getSimpleName();

    private Button btnrefresh;

    private HorizontalScrollView hsc;
    private RoundedImageView image1;
    private RoundedImageView image2;
    private RoundedImageView image3;

    private ArrayList<ImageView> imgList;
    private GestureDetector gestureDetector = null;

    private LinearLayout.LayoutParams layoutParams;

    private int nWidth, nHeight, nImgHeight;
    private int selectIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view4);

        init();
    }

    private void init(){

        //제스처 이벤트
        gestureDetector = new GestureDetector(getApplicationContext(), new MyGestureDetector());

        hsc = (HorizontalScrollView) findViewById(R.id.hsc);
        image1 = (RoundedImageView) findViewById(R.id.image1);
        image2 = (RoundedImageView) findViewById(R.id.image2);
        image3 = (RoundedImageView) findViewById(R.id.image3);

        //이미지 개수
        imgList = new ArrayList<ImageView>();

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels - dip2px(this, 39);
        nHeight = displayMetrics.heightPixels;
        nImgHeight = (int) ((float) ((320) * (float) ((float) nWidth / 960)));

        layoutParams = new LinearLayout.LayoutParams(nWidth, nImgHeight);

        //이미지 설정
        for(int i=0; i < 3; i++) {

            if(i == 0) {

                //마진 설정
                layoutParams.setMargins(0,0,0,0);

                image1.setImageResource(R.drawable.a);
                image1.setAdjustViewBounds(true);
                image1.setScaleType(ImageView.ScaleType.FIT_XY);
                image1.setLayoutParams(layoutParams);
                image1.setClipToOutline(true);
            }else if(i == 1) {

                //마진 설정
                layoutParams.setMargins(10,0,0,0);

                image2.setImageResource(R.drawable.b);
                image2.setAdjustViewBounds(true);
                image2.setScaleType(ImageView.ScaleType.FIT_XY);
                image2.setLayoutParams(layoutParams);
                image2.setClipToOutline(true);
            }else if(i == 2) {

                //마진 설정
                layoutParams.setMargins(10,0,0,0);

                image3.setImageResource(R.drawable.a);
                image3.setAdjustViewBounds(true);
                image3.setScaleType(ImageView.ScaleType.FIT_XY);
                image3.setLayoutParams(layoutParams);
                image3.setClipToOutline(true);
            }
        }

        //이미지 리스트
        imgList.add(image1);
        imgList.add(image2);
        imgList.add(image3);

        hsc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        });

        btnrefresh = findViewById(R.id.btnrefresh);
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshInit();
            }
        });
    }

    private void prevImage(){

        hsc.smoothScrollTo((int) hsc.getScrollX() - nWidth,0);
    }

    private void nextImage(){

        hsc.smoothScrollTo((int) hsc.getScrollX() + nWidth,0);
    }

    private void refreshInit(){

        //이미지 개수
        imgList.clear();

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels - dip2px(this, 39);
        nHeight = displayMetrics.heightPixels;
        nImgHeight = (int) ((float) ((320) * (float) ((float) nWidth / 960)));

        layoutParams = new LinearLayout.LayoutParams(nWidth, nImgHeight);

        //이미지 설정
        for(int i=0; i < 3; i++) {

            if(i == 0) {

                //마진 설정
                layoutParams.setMargins(0,0,0,0);

                image1.setImageResource(R.drawable.b);
                image1.setAdjustViewBounds(true);
                image1.setScaleType(ImageView.ScaleType.FIT_XY);
                image1.setLayoutParams(layoutParams);
                image1.setClipToOutline(true);
            }else if(i == 1) {

                //마진 설정
                layoutParams.setMargins(10,0,0,0);

                image2.setImageResource(R.drawable.a);
                image2.setAdjustViewBounds(true);
                image2.setScaleType(ImageView.ScaleType.FIT_XY);
                image2.setLayoutParams(layoutParams);
                image2.setClipToOutline(true);
            }else if(i == 2) {

                //마진 설정
                layoutParams.setMargins(10,0,0,0);

                image3.setImageResource(R.drawable.b);
                image3.setAdjustViewBounds(true);
                image3.setScaleType(ImageView.ScaleType.FIT_XY);
                image3.setLayoutParams(layoutParams);
                image3.setClipToOutline(true);
            }
        }

        //이미지 리스트
        imgList.add(image1);
        imgList.add(image2);
        imgList.add(image3);
    }

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getX() < e2.getX()) {
                selectIndex = getVisibleViews("left");
            } else {
                selectIndex = getVisibleViews("right");
            }

            hsc.smoothScrollTo(imgList.get(selectIndex).getLeft(), 0);

            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            Log.e(TAG, "selectIndex:" + selectIndex);

            return super.onSingleTapUp(e);
        }
    }

    private int getVisibleViews(String direction) {
        Rect hitRect = new Rect();
        int position = 0;
        int rightCounter = 0;
        for (int i = 0; i < imgList.size(); i++) {
            if (imgList.get(i).getLocalVisibleRect(hitRect)) {
                if (direction.equals("left")) {
                    position = i;
                    break;
                } else if (direction.equals("right")) {
                    rightCounter++;
                    position = i;
                    if (rightCounter == 2)
                        break;
                }
            }
        }
        return position;
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
