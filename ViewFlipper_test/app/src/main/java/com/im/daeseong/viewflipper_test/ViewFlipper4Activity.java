package com.im.daeseong.viewflipper_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class ViewFlipper4Activity extends AppCompatActivity {

    private static final String TAG = ViewFlipper4Activity.class.getSimpleName();

    private ViewFlipper viewFlipper1;
    private float lastX;
    private float currentX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper4);

        setTitle(TAG);

        viewFlipper1 = (ViewFlipper)findViewById(R.id.viewFlipper1);
        viewFlipper1.startFlipping();
        viewFlipper1.setFlipInterval(3000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        viewFlipper1.stopFlipping();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                //터치시작
                lastX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                //터치종료
                currentX = event.getX();

                //왼쪽 -> 오른쪽
                if (lastX < currentX)
                {
                    //첫번째화면이면
                    if (viewFlipper1.getDisplayedChild() == 0)
                        break;

                    //애니메이션 설정
                    viewFlipper1.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper1.setOutAnimation(this, R.anim.out_to_right);
                    viewFlipper1.showNext();
                }

                //오른쪽 -> 왼쪽
                if (lastX > currentX)
                {
                    //첫번째화면이면
                    if (viewFlipper1.getDisplayedChild() == 1)
                        break;

                    //애니메이션 설정
                    viewFlipper1.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper1.setOutAnimation(this, R.anim.out_to_left);
                    viewFlipper1.showPrevious();
                }
                break;
            }
        }
        return false;
    }

}
