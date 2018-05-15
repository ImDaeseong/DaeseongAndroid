package com.im.daeseong.swipe_test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Swipe1Activity extends AppCompatActivity {

    private static final String TAG = Swipe1Activity.class.getSimpleName();

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe1);

        iv1 = (ImageView)findViewById(R.id.iv1);
        iv1.setOnTouchListener(new MySwipeListener(this));
    }

    private class MySwipeListener extends SwipeListener{

        public MySwipeListener(Context context) {
            super(context);
        }

        @Override
        public boolean swipeUp() {

            Log.e(TAG, "swipeUp");

            return true;
        }

        @Override
        public boolean swipeDown() {

            Log.e(TAG, "swipeDown");

            return true;
        }

        @Override
        public boolean swipeLeft() {

            Log.e(TAG, "swipeLeft");

            return true;
        }

        @Override
        public boolean swipeRight() {

            Log.e(TAG, "swipeRight");

            return true;
        }
    }

}
