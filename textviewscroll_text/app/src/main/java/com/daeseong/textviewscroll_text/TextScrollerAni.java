package com.daeseong.textviewscroll_text;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;

public class TextScrollerAni {

    private static final String TAG = TextScrollerAni.class.getSimpleName();
    private static final long SCROLL_DELAY = 5000; // 스크롤 간 지연 시간 (5초)
    private static final long ANIMATION_DURATION = 1000; // 애니메이션 지속 시간 (1초)

    private TextView tv1;
    private TextView tv2;
    private List<urlApi.urlItem> items;
    private int currentItemIndex = 0;
    private Handler handler;
    private Runnable scrollRunnable;
    private View.OnClickListener clickListener;

    public TextScrollerAni(TextView tv1, TextView tv2) {
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.items = urlApi.getInstance().getItem();

        initializeTextViews();
        setupHandler();
        setupClickListener();
    }

    // TextView 초기화
    private void initializeTextViews() {
        if (items != null && !items.isEmpty()) {
            tv1.setText(items.get(currentItemIndex).getText());
            tv2.setText(items.get((currentItemIndex + 1) % items.size()).getText());
        }
    }

    // Handler 및 Runnable 설정
    private void setupHandler() {
        handler = new Handler();
        scrollRunnable = new Runnable() {
            @Override
            public void run() {
                scrollTexts();
                handler.postDelayed(this, SCROLL_DELAY);
            }
        };
    }

    // 클릭 리스너 설정
    private void setupClickListener() {
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlApi.urlItem currentItem = getCurrentItem();
                if (currentItem != null) {
                    Log.d(TAG, currentItem.getText() + " : " + currentItem.getUrl());
                }
            }
        };
        tv1.setOnClickListener(clickListener);
        tv2.setOnClickListener(clickListener);
    }

    // 애니메이션 시작
    public void start() {
        handler.postDelayed(scrollRunnable, SCROLL_DELAY);
    }

    // 애니메이션 중지
    public void stop() {
        handler.removeCallbacks(scrollRunnable);
    }

    // 현재 아이템 반환
    private urlApi.urlItem getCurrentItem() {
        if (items != null && !items.isEmpty()) {
            return items.get(currentItemIndex);
        }
        return null;
    }

    // 텍스트 스크롤 애니메이션 실행
    private void scrollTexts() {
        Animation anim1 = new TranslateAnimation(0, 0, 0, -tv1.getHeight());
        anim1.setDuration(ANIMATION_DURATION);
        anim1.setFillAfter(true);
        tv1.startAnimation(anim1);

        Animation anim2 = new TranslateAnimation(0, 0, tv1.getHeight(), 0);
        anim2.setDuration(ANIMATION_DURATION);
        anim2.setFillAfter(true);
        tv2.startAnimation(anim2);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateTextViews();
            }
        }, ANIMATION_DURATION);
    }

    // TextView 업데이트
    private void updateTextViews() {
        tv1.setText(tv2.getText());
        tv1.clearAnimation();
        tv2.clearAnimation();

        // tv2를 화면 아래로 재배치
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tv2.getLayoutParams();
        params.topToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        tv2.setLayoutParams(params);

        // 다음 사이클을 위해 currentItemIndex 업데이트
        currentItemIndex = (currentItemIndex + 1) % items.size();
        tv2.setText(items.get((currentItemIndex + 1) % items.size()).getText());
    }

}
