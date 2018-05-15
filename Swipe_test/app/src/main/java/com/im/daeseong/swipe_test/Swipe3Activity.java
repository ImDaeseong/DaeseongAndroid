package com.im.daeseong.swipe_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Swipe3Activity extends AppCompatActivity {

    private static final String TAG = Swipe3Activity.class.getSimpleName();

    private SwipeFrameLayout swipeFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe3);

        swipeFrameLayout = (SwipeFrameLayout)findViewById(R.id.swFL);
        swipeFrameLayout.setOnSwipeListener(new SwipeFrameLayout.OnSwipeFrameListener() {
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
