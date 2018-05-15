package com.im.daeseong.swipe_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Swipe4Activity extends AppCompatActivity {

    private static final String TAG = Swipe4Activity.class.getSimpleName();

    private SwipeConstraintLayout swipeConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe4);

        swipeConstraintLayout = (SwipeConstraintLayout)findViewById(R.id.swCL);
        swipeConstraintLayout.setOnSwipeListener(new SwipeConstraintLayout.OnSwipeConstraintListener() {
            @Override
            public void swipeLeft() {
                Log.e(TAG, " swipeLeft ");
            }

            @Override
            public void swipeRight() {
                Log.e(TAG, " swipeRight ");
            }

            @Override
            public void swipeUp() {
                Log.e(TAG, " swipeUp ");
            }

            @Override
            public void swipeDown() {
                Log.e(TAG, " swipeDown ");
            }
        });

    }


}
