package com.daeseong.horizontalscrollview_flingtest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HorizontalScrollView5Activity extends AppCompatActivity {

    private static final String TAG = HorizontalScrollView5Activity.class.getSimpleName();

    private Button btnrefresh;

    private HorizontalScrollView hsc;
    private LinearLayout llayout;

    private int nWidth, nHeight, nImgWidth, nImgHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view5);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init(){

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels;
        nHeight = displayMetrics.heightPixels;
        nImgWidth = nWidth - dip2px(this, 29);
        nImgHeight = (int) ((float) ((320) * (float) ((float) nWidth / 960)));

        hsc = (HorizontalScrollView) findViewById(R.id.hsc);
        llayout = (LinearLayout)findViewById(R.id.llayout);

        //데이터 로드
        addRoundedImageView();

        ViewTreeObserver viewTreeObserver =  llayout.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                //로드 완료시 HorizontalScrollView 제일 마지막 이미지 선택하기
                selectPos(2);
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

    private void refreshInit(){

        llayout.removeAllViews();
        addRoundedImageView();
    }

    private void addRoundedImageView(){

        for(int i=0; i < 3; i++){

            RoundedImageView roundedImageView = new RoundedImageView(this);
            roundedImageView.setTag(i);

            if(i == 0) {
                roundedImageView.setImageResource(R.drawable.a);
                roundedImageView.setAdjustViewBounds(true);
                roundedImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                roundedImageView.setClipToOutline(true);
            }else if(i == 1){
                roundedImageView.setImageResource(R.drawable.b);
                roundedImageView.setAdjustViewBounds(true);
                roundedImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                roundedImageView.setClipToOutline(true);
            }else if(i == 2){
                roundedImageView.setImageResource(R.drawable.a);
                roundedImageView.setAdjustViewBounds(true);
                roundedImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                roundedImageView.setClipToOutline(true);
            }

            roundedImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "roundedImageView Click");
                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(nImgWidth, nImgHeight);
            roundedImageView.setLayoutParams(params);

            //마진 설정
            if (i == 0){
                params.setMargins(0,0,0,0);
            }else {
                params.setMargins(10,0,0,0);
            }
            llayout.addView(roundedImageView);
        }
    }

    private void selectPos(int nSelectIndex){
        int nSelectWidth = llayout.getChildAt(nSelectIndex).getWidth();
        int nSelectLeft = llayout.getChildAt(nSelectIndex).getLeft();
        int nIndex = nWidth / nSelectWidth;
        int nPosScroll = ( nSelectLeft - ( ( nIndex * nSelectWidth ) / 2 ) + nSelectWidth/2 );
        hsc.smoothScrollTo(nPosScroll, 0);
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
