package com.daeseong.floatingview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class FloatinView4Activity extends AppCompatActivity {

    private static final String TAG = FloatinView4Activity.class.getSimpleName();

    private View top1, top2, top3;
    private View v1, v2, v3, v4, v5, v6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatin_view4);

        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);

        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);

        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);

        v5 = findViewById(R.id.v5);
        v6 = findViewById(R.id.v6);

        init();
    }

    private void init() {
        showFloatingView1();
        showFloatingView2();
        showFloatingView3();
    }

    private void showFloatingView1() {

        ViewGroup rootView = (ViewGroup)top1;

        FloatingView1 floatingview = new FloatingView1(rootView);
        floatingview.getFloatingview().setVisibility(View.VISIBLE);

        rootView.addView(floatingview.getFloatingview());

        floatingview.getFloatingview().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                floatingview.getFloatingview().getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingview.getFloatingview().getLayoutParams();

                layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
                layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.startToStart = R.id.v1;
                layoutParams.leftMargin = 0;
                layoutParams.endToEnd = R.id.v2;
                layoutParams.rightMargin = 0;
                layoutParams.topToTop = R.id.top1;
                layoutParams.bottomToBottom = R.id.top1;

                //레이아웃 파라미터 적용
                floatingview.getFloatingview().setLayoutParams(layoutParams);

                //슬라이딩 효과
                Utils.slideingView(floatingview.getFloatingview());

                floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"));
                floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"));

            }
        });
        floatingview.getFloatingview().requestLayout();
    }

    private void showFloatingView2() {

        ViewGroup rootView = (ViewGroup)top2;

        FloatingView2 floatingview = new FloatingView2(rootView);
        floatingview.getFloatingview().setVisibility(View.VISIBLE);

        rootView.addView(floatingview.getFloatingview());

        floatingview.getFloatingview().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                floatingview.getFloatingview().getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingview.getFloatingview().getLayoutParams();

                layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
                layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.startToStart = R.id.v3;
                layoutParams.leftMargin = v3.getWidth();
                layoutParams.endToEnd = R.id.v4;
                layoutParams.rightMargin = v4.getWidth();
                layoutParams.topToTop = R.id.top2;
                layoutParams.bottomToBottom = R.id.top2;

                //레이아웃 파라미터 적용
                floatingview.getFloatingview().setLayoutParams(layoutParams);

                //슬라이딩 효과
                Utils.slideingView(floatingview.getFloatingview());

                floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"));
                floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"));

            }
        });
        floatingview.getFloatingview().requestLayout();
    }

    private void showFloatingView3() {

        ViewGroup rootView = (ViewGroup)top3;

        FloatingView3 floatingview = new FloatingView3(rootView);
        floatingview.getFloatingview().setVisibility(View.VISIBLE);

        rootView.addView(floatingview.getFloatingview());

        floatingview.getFloatingview().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                floatingview.getFloatingview().getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingview.getFloatingview().getLayoutParams();

                layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
                layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.startToStart = R.id.v5;
                layoutParams.leftMargin = v5.getWidth() +  Utils.dip2px(FloatinView4Activity.this, 10);
                layoutParams.endToEnd = R.id.v6;
                layoutParams.rightMargin = v6.getWidth() +  Utils.dip2px(FloatinView4Activity.this, 10);
                layoutParams.topToTop = R.id.top3;
                layoutParams.bottomToBottom = R.id.top3;

                //레이아웃 파라미터 적용
                floatingview.getFloatingview().setLayoutParams(layoutParams);

                //슬라이딩 효과
                Utils.slideingView(floatingview.getFloatingview());

                floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"));
                floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"));

            }
        });
        floatingview.getFloatingview().requestLayout();
    }

}