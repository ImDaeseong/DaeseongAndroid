package com.daeseong.floatingview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class FloatinView2Activity extends AppCompatActivity {

    private static final String TAG = FloatinView2Activity.class.getSimpleName();

    private View top1, top2, top3;
    private View v1, v2, v3, v4, v5, v6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatin_view2);

        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);

        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFloatingView1();
            }
        });

        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFloatingView2();
            }
        });

        v5 = findViewById(R.id.v5);
        v6 = findViewById(R.id.v6);
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFloatingView3();
            }
        });

        init();
    }

    private void init() {

    }

    private void showFloatingView1() {

        ViewGroup rootView = (ViewGroup)top1;

        FloatingView2 floatingview = new FloatingView2(rootView);
        floatingview.getFloatingview().setVisibility(View.VISIBLE);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingview.getFloatingview().getLayoutParams();

        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;

        //왼쪽 마진 위치
        layoutParams.startToStart = R.id.v1;
        layoutParams.leftMargin = 0;

        //오른쪽 마진 위치
        layoutParams.endToEnd = R.id.v2;
        layoutParams.rightMargin = 0;

        //수직 가운데 정렬
        layoutParams.topToTop = R.id.top1;
        layoutParams.bottomToBottom = R.id.top1;

        floatingview.getFloatingview().setLayoutParams(layoutParams);
        rootView.addView(floatingview.getFloatingview());

        floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"));
        floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"));
    }

    private void showFloatingView2() {

        ViewGroup rootView = (ViewGroup)top2;

        FloatingView2 floatingview = new FloatingView2(rootView);
        floatingview.getFloatingview().setVisibility(View.VISIBLE);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingview.getFloatingview().getLayoutParams();

        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;

        //왼쪽 마진 위치
        layoutParams.startToStart = R.id.v3;
        layoutParams.leftMargin = v3.getWidth();

        //오른쪽 마진 위치
        layoutParams.endToEnd = R.id.v4;
        layoutParams.rightMargin = v4.getWidth();

        //수직 가운데 정렬
        layoutParams.topToTop = R.id.top2;
        layoutParams.bottomToBottom = R.id.top2;

        floatingview.getFloatingview().setLayoutParams(layoutParams);
        rootView.addView(floatingview.getFloatingview());

        floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"));
        floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"));
    }

    private void showFloatingView3() {

        ViewGroup rootView = (ViewGroup)top3;

        FloatingView2 floatingview = new FloatingView2(rootView);
        floatingview.getFloatingview().setVisibility(View.VISIBLE);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingview.getFloatingview().getLayoutParams();

        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;

        //왼쪽 마진 위치
        layoutParams.startToStart = R.id.v5;
        layoutParams.leftMargin = v5.getWidth() +  Utils.dip2px(this, 10);

        //오른쪽 마진 위치
        layoutParams.endToEnd = R.id.v6;
        layoutParams.rightMargin = v6.getWidth() +  Utils.dip2px(this, 10);

        //수직 가운데 정렬
        layoutParams.topToTop = R.id.top3;
        layoutParams.bottomToBottom = R.id.top3;

        floatingview.getFloatingview().setLayoutParams(layoutParams);
        rootView.addView(floatingview.getFloatingview());

        floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"));
        floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"));
    }
}