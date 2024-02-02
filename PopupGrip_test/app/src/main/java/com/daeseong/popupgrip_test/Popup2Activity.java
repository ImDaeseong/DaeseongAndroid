package com.daeseong.popupgrip_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;

public class Popup2Activity extends AppCompatActivity {

    private static final String TAG = Popup2Activity.class.getSimpleName();

    private View popup_layout;
    private View grip;

    private int nStartPopupY;//팝업창 클릭시 Y
    private float nStartGripY;//grip 클릭시 Y
    private int nLimit;//팝업창 시작 Y
    private float nGripY;//grip 시작 Y
    private int nloadPopupY;//팝어창 호출시 Y

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_popup2);

        setFinishOnTouchOutside(false);

        init();

        changeDisplay();
    }

    private void changeDisplay() {

        // 레이아웃이 생성된 후에 화면 크기 구하기
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                //화면에서 2/3 이상 아래로 드래그하면 종료 시키기 위해 최초 실행시 높이값
                nLimit = (getWindow().getDecorView().getHeight() * 2) / 3;
                //Log.e(TAG, "nLimit:" + nLimit);

                nloadPopupY = (int) popup_layout.getY();
                //Log.e(TAG, "nloadPopupY:" + nloadPopupY);

                nGripY = grip.getY();
                //Log.e(TAG, "nGripY:" + nGripY);

                //리스너를 제거하여 중복 호출 방지
                getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getAttributes().windowAnimations = R.style.BottomPopupSlideTheme;
        getWindow().setGravity(Gravity.BOTTOM);
    }

    private void init(){

        popup_layout = findViewById(R.id.popup_layout);

        grip = findViewById(R.id.grip);
        grip.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        nStartPopupY = (int) popup_layout.getY();
                        nStartGripY = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        //뷰 위치 계산
                        float deltaY = event.getRawY() - nStartGripY;

                        //현재 팝업창 위치 계산
                        int nCurrentPopupY = (int) (nStartPopupY + deltaY);

                        // 현재 위치 nCurrentPopupY 값이 시작시 nStartPopupY 값보다 위로가면 않되므로 체크
                        if (nCurrentPopupY >= nStartPopupY) {
                            movePopup(nCurrentPopupY);
                        }
                        break;

                    case MotionEvent.ACTION_UP:

                        // 팝업창 위치가 nLimit 값의 2/3 이상이면 종료
                        if ((popup_layout.getY() - nStartPopupY) > (nLimit / 2)) {

                            int nY = getWindow().getDecorView().getHeight() - (int)(nGripY * 2);

                            popup_layout.animate().y(nY).setDuration(200).withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    //Log.e(TAG, "팝업창 아래로 이동");
                                }
                            }).start();

                        } else {
                            //Log.e(TAG, "팝업창 원대래대로");
                            popup_layout.animate().y(nloadPopupY).setDuration(200).start();
                            grip.animate().y(nGripY).setDuration(200).start();
                        }
                        break;
                }
                return true;
            }
        });
    }

    // 팝업창 위치 이동
    private void movePopup(int nCurrentY) {
        popup_layout.setY(nCurrentY);
    }

}