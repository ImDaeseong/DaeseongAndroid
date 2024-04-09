package com.daeseong.floatingview_test;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class FloatingImgBottomLayout {

    private final String tag = FloatingImgBottomLayout.class.getSimpleName();

    private final ImageView floatingView;

    public FloatingImgBottomLayout(Context context) {

        floatingView = new ImageView(context);

        // 배경 이미지 설정
        floatingView.setImageResource(R.drawable.bgimg);

        float density = context.getResources().getDisplayMetrics().density;
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;

        FrameLayout.LayoutParams paramsIv = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        // 넓이
        paramsIv.width = (int) (screenWidth - (16 * density * 2));

        // 높이
        int desiredHeight = (int) (60 * density);
        paramsIv.height = (int) (paramsIv.width / 288f * desiredHeight);

        floatingView.setLayoutParams(paramsIv);

        // FIT_CENTER 비율을 유지하면서 뷰 크기에 맞게 조정
        floatingView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        floatingView.setClipToOutline(true);
        floatingView.setAdjustViewBounds(true);
    }

    public View getFloatingView() {
        return floatingView;
    }

    public void show(ViewGroup parent) {

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        // 마진 제거
        layoutParams.setMargins(0, 0, 0, 0);

        floatingView.setLayoutParams(layoutParams);

        parent.addView(floatingView);
    }

    public void hide() {
        try {
            ((ViewGroup) floatingView.getParent()).removeView(floatingView);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
