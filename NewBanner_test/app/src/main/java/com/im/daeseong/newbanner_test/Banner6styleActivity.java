package com.im.daeseong.newbanner_test;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.newbanner_test.Banner6_style.BannerAdapter;

public class Banner6styleActivity extends AppCompatActivity {

    private static final String TAG = Banner6styleActivity.class.getSimpleName();

    private ViewPager viewPager;

    private int nCurrent = 0;
    private int TotalCount;

    private int mCurrentPosition = 0;

    private int[] imgs = new int[]{R.drawable.number1,R.drawable.number2,R.drawable.number3,R.drawable.number4};

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {

            //viewPager.setCurrentItem(nCurrent%TotalCount, true);
            //nCurrent++;

            mCurrentPosition = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(mCurrentPosition);
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner6style);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageSelected(int position){

                Log.e(TAG, "position:" + position);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 2000);
            }

        });
        BannerAdapter adapter = new BannerAdapter(this, imgs);

        //롤링 개수
        int half = Short.MAX_VALUE / 2;
        half = half - half % imgs.length;
        Log.e(TAG, "half:" + half);

        viewPager.setCurrentItem(half );
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        handler.sendEmptyMessageDelayed(0, 2000);
    }

}
