package com.daeseong.bottomsheetdialog_test;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Main9Activity extends AppCompatActivity {

    private static final String TAG = Main9Activity.class.getSimpleName();

    // UI 요소
    private ConstraintLayout bottomSheet;
    private Button button1;
    private View bottomSheetHandle;

    // 드래그 관련 변수
    private float initialY = 0f;
    private float lastY = 0f;
    private int bottomSheetHeight = 0;
    private boolean isDragging = false;
    private long startTime = 0L;
    private float dragVelocity = 0f;
    private int lastDragDirection = 0; // 1: 아래로, -1: 위로, 0: 초기 상태

    private static final long ANIMATION_DURATION = 300L; // 애니메이션 지속 시간
    private static final float DISMISS_THRESHOLD = 0.4f; // 바텀 시트를 닫기 위한 임계값 (전체 높이의 40%)
    private static final float VELOCITY_THRESHOLD = 800f; // 빠른 스와이프 감지를 위한 속도 임계값
    private static final float DRAG_SENSITIVITY = 0.8f; // 드래그 민감도 (낮을수록 덜 민감)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        initializeViews();
        setupBottomSheet();
        setupClickListeners();
    }

    // UI 요소 초기화
    private void initializeViews() {
        bottomSheet = findViewById(R.id.bottomSheet);
        button1 = findViewById(R.id.button1);
        bottomSheetHandle = findViewById(R.id.bottomSheetHandle);
        bottomSheetHandle.setClickable(true);
        bottomSheetHandle.setFocusable(true);
    }

    // 바텀 시트 초기 설정
    private void setupBottomSheet() {
        bottomSheet.setVisibility(View.GONE);

        // 바텀 시트의 높이를 측정
        bottomSheet.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bottomSheetHeight = bottomSheet.getHeight();
                bottomSheet.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    // 클릭 리스너 설정
    private void setupClickListeners() {
        // 버튼 클릭 시 바텀 시트 표시/숨김 전환
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheet.getVisibility() == View.GONE) {
                    showBottomSheet();
                } else {
                    hideBottomSheet();
                }
            }
        });

        // 바텀 시트 핸들의 터치 이벤트 처리
        bottomSheetHandle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return handleTouchEvent(event);
            }
        });
    }

    // 터치 이벤트 처리
    private boolean handleTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDragging = true;
                initialY = event.getRawY();
                lastY = event.getRawY();
                startTime = System.currentTimeMillis();
                lastDragDirection = 0;
                return true;

            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    float deltaY = (event.getRawY() - lastY) * DRAG_SENSITIVITY;
                    moveBottomSheet(deltaY);
                    updateDragMetrics(event);
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isDragging) {
                    isDragging = false;
                    handleDragEnd();
                    return true;
                }
                break;
        }
        return false;
    }

    // 바텀 시트 이동
    private void moveBottomSheet(float deltaY) {
        float newTranslationY = bottomSheet.getTranslationY() + deltaY;
        if (newTranslationY >= 0) {
            bottomSheet.setTranslationY(newTranslationY);
        }
    }

    // 드래그 관련 지표 업데이트
    private void updateDragMetrics(MotionEvent event) {
        float deltaY = event.getRawY() - lastY;
        lastDragDirection = deltaY > 0 ? 1 : -1;

        long dragDuration = System.currentTimeMillis() - startTime;
        dragVelocity = dragDuration > 0 ? (event.getRawY() - initialY) / dragDuration * 1000 : 0f;

        lastY = event.getRawY();
    }

    // 드래그 종료 처리
    private void handleDragEnd() {
        if (shouldDismissBottomSheet()) {
            hideBottomSheetImmediately();
        } else {
            resetBottomSheet();
        }
    }

    // 바텀 시트를 닫아야 하는지 결정하는 함수
    private boolean shouldDismissBottomSheet() {
        boolean isDraggedFarEnough = bottomSheet.getTranslationY() > bottomSheetHeight * DISMISS_THRESHOLD;
        boolean isLastDragDirectionDown = lastDragDirection > 0;
        boolean isDragVelocityHigh = dragVelocity > VELOCITY_THRESHOLD;

        return (isDraggedFarEnough && isLastDragDirectionDown) || (isDragVelocityHigh && isLastDragDirectionDown);
    }

    // 바텀 시트를 즉시 숨김 (애니메이션 없이)
    private void hideBottomSheetImmediately() {
        bottomSheet.setVisibility(View.GONE);
        bottomSheet.setTranslationY(0f);
    }

    // 바텀 시트를 원래 위치로 리셋
    private void resetBottomSheet() {
        float currentTranslationY = bottomSheet.getTranslationY();
        animateBottomSheet(currentTranslationY, 0f, ANIMATION_DURATION, null);
    }

    // 바텀 시트 표시
    private void showBottomSheet() {
        bottomSheet.setVisibility(View.VISIBLE);
        bottomSheet.setTranslationY(bottomSheetHeight);
        animateBottomSheet(bottomSheetHeight, 0f, ANIMATION_DURATION, null);
    }

    // 바텀 시트 숨김 (애니메이션 사용)
    private void hideBottomSheet() {
        animateBottomSheet(bottomSheet.getTranslationY(), bottomSheetHeight, ANIMATION_DURATION, new Runnable() {
            @Override
            public void run() {
                bottomSheet.setVisibility(View.GONE);
                bottomSheet.setTranslationY(0f);
            }
        });
    }

    // 바텀 시트 애니메이션
    private void animateBottomSheet(float fromTranslationY, float toTranslationY, long duration, final Runnable endAction) {
        ValueAnimator animator = ValueAnimator.ofFloat(fromTranslationY, toTranslationY);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bottomSheet.setTranslationY((float) animation.getAnimatedValue());
            }
        });

        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

        if (endAction != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    endAction.run();
                }
            });
        }
    }
}