package com.daeseong.floatingview_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class FloatingImgTopLayout {

    private final String tag = FloatingImgTopLayout.class.getSimpleName();

    private final ImageView floatingView;

    public FloatingImgTopLayout(Context context) {

        floatingView = new ImageView(context);

        // 배경 이미지 설정
        floatingView.setImageResource(R.drawable.bgimg);

        float density = context.getResources().getDisplayMetrics().density;
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;

        FrameLayout.LayoutParams paramsIv = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        // 넓이
        paramsIv.width = screenWidth - ((int) (26 * density) + (int) (11 * density));

        // 높이
        int desiredHeight = (int) (54 * density);
        paramsIv.height = (int) (paramsIv.width / 283f * desiredHeight);

        floatingView.setLayoutParams(paramsIv);

        // FIT_CENTER 비율을 유지하면서 뷰 크기에 맞게 조정
        floatingView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        floatingView.setClipToOutline(true);
        floatingView.setAdjustViewBounds(true);

        floatingView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                try {
                    hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public View getFloatingView() {
        return floatingView;
    }

    public void show(ViewGroup parent, int parentId) {

        View pView = parent.findViewById(parentId);
        View tbView = parent.findViewById(R.id.cL1);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        // 마진
        float density = parent.getContext().getResources().getDisplayMetrics().density;
        layoutParams.leftMargin = (int) (26 * density);
        layoutParams.rightMargin = (int) (11 * density);

        pView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int tHeight = tbView.getHeight();
                int topMargin = pView.getTop() + (tHeight - (int) (12 * density));

                layoutParams.topMargin = topMargin;
                floatingView.setLayoutParams(layoutParams);
                parent.addView(floatingView);
            }
        });
    }

    public void hide() {
        try {
            ((ViewGroup) floatingView.getParent()).removeView(floatingView);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
