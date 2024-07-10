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

public class TextScrollerCL {

    private static final String TAG = TextScrollerCL.class.getSimpleName();

    private static final long ANIMATION_DURATION = 1000; // 애니메이션 지속 시간 (밀리초)
    private static final long TEXT_CHANGE_INTERVAL = 5000; // 텍스트 변경 간격 (밀리초)

    private final View view1;
    private final View view2;
    private int currentIndex = 0;
    private boolean isViewVisible = true;
    private boolean isAnimating = false;
    private ValueAnimator animator;
    private boolean isDirectUp = true;

    public TextScrollerCL(@NonNull View view1, @NonNull View view2) {
        this.view1 = view1;
        this.view2 = view2;
        setupViews();
        startAnimation();
    }

    private void setupViews() {

        View.OnClickListener onClickListener = v -> {
            urlApi.urlItem item = getCurrentItem();
            if (item != null) {
                Log.e(TAG, item.getText() + " : " + item.getUrl());
            }
        };
        view1.setOnClickListener(onClickListener);
        view2.setOnClickListener(onClickListener);

        List<urlApi.urlItem> items = urlApi.getInstance().getItem();
        if (items != null && !items.isEmpty()) {
            updateNextText(view1, currentIndex);
        }
        view2.setVisibility(View.INVISIBLE);
        view2.setTranslationY(view1.getHeight());
    }

    private void startAnimation() {

        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(ANIMATION_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(this::updateViewPositions);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
                List<urlApi.urlItem> items = urlApi.getInstance().getItem();
                if (items != null && !items.isEmpty()) {
                    View view = getInvisibleView();
                    updateNextText(view, (currentIndex + 1) % items.size());
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                swapViews();
                animator.setStartDelay(TEXT_CHANGE_INTERVAL - ANIMATION_DURATION);
                animator.start();
            }
        });
        animator.setStartDelay(TEXT_CHANGE_INTERVAL);
        animator.start();
    }

    private void updateViewPositions(ValueAnimator animation) {

        float value = (float) animation.getAnimatedValue();
        View visibleView = getVisibleView();
        View invisibleView = getInvisibleView();

        if (isDirectUp) {
            visibleView.setTranslationY(-value * visibleView.getHeight());
            invisibleView.setTranslationY((1 - value) * invisibleView.getHeight());
        } else {
            visibleView.setTranslationY(value * visibleView.getHeight());
            invisibleView.setTranslationY(-(1 - value) * invisibleView.getHeight());
        }
    }

    private void updateNextText(View view, int index) {

        List<urlApi.urlItem> items = urlApi.getInstance().getItem();
        if (items == null || items.isEmpty()) {
            return;
        }

        TextView textView = view.findViewById(view == view1 ? R.id.tv1 : R.id.tv2);
        if (textView != null) {
            textView.setText(items.get(index).getText());
        }
    }

    private void swapViews() {

        View visibleView = getVisibleView();
        View invisibleView = getInvisibleView();

        visibleView.setVisibility(View.INVISIBLE);
        invisibleView.setVisibility(View.VISIBLE);

        if (isDirectUp) {
            visibleView.setTranslationY(visibleView.getHeight());
            invisibleView.setTranslationY(0);
        } else {
            visibleView.setTranslationY(-visibleView.getHeight());
            invisibleView.setTranslationY(0);
        }

        List<urlApi.urlItem> items = urlApi.getInstance().getItem();
        if (items != null && !items.isEmpty()) {
            currentIndex = (currentIndex + 1) % items.size();
        }

        isViewVisible = !isViewVisible;
    }

    private View getVisibleView() {
        return isViewVisible ? view1 : view2;
    }

    private View getInvisibleView() {
        return isViewVisible ? view2 : view1;
    }

    private urlApi.urlItem getCurrentItem() {
        List<urlApi.urlItem> items = urlApi.getInstance().getItem();
        if (items != null && !items.isEmpty()) {
            return items.get(currentIndex);
        }
        return null;
    }

    public void start() {
        if (animator != null && !isAnimating) {
            animator.start();
            isAnimating = true;
        }
    }

    public void stop() {
        if (animator != null && isAnimating) {
            animator.cancel();
            isAnimating = false;
        }
    }

    public void setDirect(boolean isDirectUp) {
        this.isDirectUp = isDirectUp;
    }
}
