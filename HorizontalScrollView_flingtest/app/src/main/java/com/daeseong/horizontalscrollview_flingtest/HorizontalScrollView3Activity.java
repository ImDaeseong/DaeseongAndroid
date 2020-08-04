package com.daeseong.horizontalscrollview_flingtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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


public class HorizontalScrollView3Activity extends AppCompatActivity {

    private static final String TAG = HorizontalScrollView3Activity.class.getSimpleName();

    private Button btnrefresh;

    private HorizontalScrollView hsc;
    private LinearLayout llayout;
    private CardView cardView1, cardView2, cardView3;
    private ImageView image1, image2, image3;

    private ArrayList<CardView> imgList;
    private GestureDetector gestureDetector = null;

    private int nWidth, nHeight, nImgHeight;
    private int selectIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view3);

        init();
    }

    private void init(){

        //제스처 이벤트
        gestureDetector = new GestureDetector(getApplicationContext(), new MyGestureDetector());

        hsc = (HorizontalScrollView) findViewById(R.id.hsc);
        llayout = (LinearLayout) findViewById(R.id.llayout);
        cardView1 = (CardView) findViewById(R.id.cardView1);
        cardView2 = (CardView) findViewById(R.id.cardView2);
        cardView3 = (CardView) findViewById(R.id.cardView3);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);

        //이미지 개수
        imgList = new ArrayList<CardView>();

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels - dip2px(this, 29);//displayMetrics.widthPixels;
        nHeight = displayMetrics.heightPixels;
        nImgHeight = (int) ((float) ((320) * (float) ((float) nWidth / 960)));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(nWidth, nImgHeight);
        cardView1.setLayoutParams(layoutParams);
        cardView2.setLayoutParams(layoutParams);
        cardView3.setLayoutParams(layoutParams);

        image1.setImageResource(R.drawable.a);
        image2.setImageResource(R.drawable.a);
        image3.setImageResource(R.drawable.a);

        //이미지 리스트
        imgList.add(cardView1);
        imgList.add(cardView2);
        imgList.add(cardView3);

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

        //llayout.removeAllViews();

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels - dip2px(this, 29);
        nHeight = displayMetrics.heightPixels;
        nImgHeight = (int) ((float) ((320) * (float) ((float) nWidth / 960)));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(nWidth, nImgHeight);
        cardView1.setLayoutParams(layoutParams);
        cardView2.setLayoutParams(layoutParams);
        cardView3.setLayoutParams(layoutParams);

        image1.setImageResource(R.drawable.b);
        image2.setImageResource(R.drawable.b);
        image3.setImageResource(R.drawable.b);

        //이미지 리스트
        imgList.add(cardView1);
        imgList.add(cardView2);
        imgList.add(cardView3);
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
