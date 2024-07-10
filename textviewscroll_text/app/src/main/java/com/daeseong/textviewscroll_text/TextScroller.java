package com.daeseong.textviewscroll_text;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

public class TextScroller {

    private static final String TAG = TextScroller.class.getSimpleName();

    private static final long ANIMATION_DURATION = 500;// 애니메이션 지속 시간 (밀리초)
    private static final long TEXT_CHANGE_INTERVAL = 5000;// 텍스트 변경 간격 (밀리초)

    private final TextView textView1;
    private final TextView textView2;
    private int currentIndex;
    private boolean isTextView1Visible = true;// textView1이 현재 보이는지 여부
    private boolean isAnimating = false;// 애니메이션 진행 중 여부
    private ValueAnimator animator;

    public TextScroller(@NonNull TextView tv1, @NonNull TextView tv2) {
        this.textView1 = tv1;
        this.textView2 = tv2;

        setupViews();
        startAnimation();
    }

    //초기화
    private void setupViews() {

        //클릭 이벤트
        View.OnClickListener onClickListener = v -> {
            urlApi.urlItem item = getCurrentItem();
            Log.e(TAG, item.getText() + " : " + item.getUrl());
        };
        textView1.setOnClickListener(onClickListener);
        textView2.setOnClickListener(onClickListener);

        //textView1 설정
        List<urlApi.urlItem> items = urlApi.getInstance().getItem();
        if (items != null && items.size() > 0) {
            textView1.setText(items.get(0).getText());
        }
        textView2.setVisibility(View.INVISIBLE);
    }

    // 애니메이션 시작
    private void startAnimation() {

        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(ANIMATION_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(this::updateTextViewPositions);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                swapTextViews();
                animator.setStartDelay(TEXT_CHANGE_INTERVAL - ANIMATION_DURATION);
                animator.start();
            }
        });
        animator.setStartDelay(TEXT_CHANGE_INTERVAL);
        animator.start();
    }

    // TextView 위치 업데이트
    private void updateTextViewPositions(ValueAnimator animation) {

        float value = (float) animation.getAnimatedValue();
        TextView visibleTextView = getVisibleTextView();
        TextView invisibleTextView = getInvisibleTextView();

        visibleTextView.setTranslationY(-value * visibleTextView.getHeight());
        invisibleTextView.setTranslationY((1 - value) * invisibleTextView.getHeight());
    }

    // TextView 교체
    private void swapTextViews() {

        TextView visibleTextView = getVisibleTextView();
        TextView invisibleTextView = getInvisibleTextView();

        visibleTextView.setVisibility(View.INVISIBLE);
        invisibleTextView.setVisibility(View.VISIBLE);

        List<urlApi.urlItem> items = urlApi.getInstance().getItem();
        if (items != null && items.size() > 0) {
            currentIndex = (currentIndex + 1) % items.size();
            invisibleTextView.setText(items.get(currentIndex).getText());
        }

        isTextView1Visible = !isTextView1Visible;
    }

    // 현재 보이는 TextView 반환
    private TextView getVisibleTextView() {
        return isTextView1Visible ? textView1 : textView2;
    }

    // 현재 보이지 않는 TextView 반환
    private TextView getInvisibleTextView() {
        return isTextView1Visible ? textView2 : textView1;
    }

    // 현재 아이템
    private urlApi.urlItem getCurrentItem() {
        List<urlApi.urlItem> items = urlApi.getInstance().getItem();
        if (items != null && items.size() > 0) {
            return items.get(currentIndex);
        }
        return null;
    }

    // 애니메이션 시작
    public void start() {
        if (animator != null && !isAnimating) {
            animator.start();
            isAnimating = true;
        }
    }

    // 애니메이션 중지
    public void stop() {
        if (animator != null && isAnimating) {
            animator.cancel();
            isAnimating = false;
        }
    }
}


