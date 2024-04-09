package com.daeseong.floatingview_test;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class FloatingDoubleImgTopLayout {

    private final String tag = FloatingDoubleImgTopLayout.class.getSimpleName();

    private final FrameLayout floatingView;

    private final ImageView bgImage;
    private final ImageView btnImage;

    public FloatingDoubleImgTopLayout(Context context) {
        floatingView = new FrameLayout(context);

        btnImage = new ImageView(context);

        // 배경 이미지 설정
        bgImage = new ImageView(context);
        bgImage.setImageResource(R.drawable.bgimg);
        bgImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        bgImage.setClipToOutline(true);
        bgImage.setAdjustViewBounds(true);
        floatingView.addView(bgImage);

        // 버튼 이미지 설정
        //btnImage.setImageResource(R.drawable.checkbox1);
        btnImage.setImageResource(android.R.color.transparent);
        btnImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        btnImage.setClipToOutline(true);
        btnImage.setAdjustViewBounds(true);

        // 버튼 오른쪽 정렬
        int imgSize = Utils.dip2px(context, 50);

        FrameLayout.LayoutParams btnLayoutParams = new FrameLayout.LayoutParams(imgSize, FrameLayout.LayoutParams.MATCH_PARENT);
        btnLayoutParams.gravity = Gravity.END;
        floatingView.addView(btnImage, btnLayoutParams);

        // 투명 버튼 클릭
        btnImage.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                try {
                    hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        float density = context.getResources().getDisplayMetrics().density;
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;

        FrameLayout.LayoutParams paramsIv = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        // 넓이
        paramsIv.width = (int) (screenWidth - ((26 * density) + (11 * density)));

        // 높이
        int desiredHeight = (int) (54 * density);
        paramsIv.height = (int) (paramsIv.width / 283f * desiredHeight);

        floatingView.setLayoutParams(paramsIv);
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
