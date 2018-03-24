package com.im.daeseong.newbanner_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.im.daeseong.newbanner_test.Banner5_style.ViewPagerIndicatorView;
import com.im.daeseong.newbanner_test.Banner8_style.AutoScrollViewPager;
import com.im.daeseong.newbanner_test.Banner8_style.ImagePagerAdapter;
import com.im.daeseong.newbanner_test.Banner8_style.itememainbanner;

import java.util.ArrayList;
import java.util.List;

public class Banner8styleActivity extends AppCompatActivity {

    private AutoScrollViewPager autoScrollViewPager;

    //private List<Integer>       imageIdList;
    private List<itememainbanner>       imageIdList;

    private ViewPagerIndicatorView BannerDotView;

    int CurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner8style);

        autoScrollViewPager = (AutoScrollViewPager)findViewById(R.id.viewPager1);

        BannerDotView = (ViewPagerIndicatorView) findViewById(R.id.indicator);

        imageIdList = new ArrayList<>();
        /*
        imageIdList.add(R.drawable.number1);
        imageIdList.add(R.drawable.number2);
        imageIdList.add(R.drawable.number3);
        imageIdList.add(R.drawable.number4);
        */

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.number1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.number2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.number3);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(),R.drawable.number4);

        itememainbanner item = new itememainbanner();
        item.setItem( "1", "naver.com", bitmap1);
        imageIdList.add(item);

        item = new itememainbanner();
        item.setItem( "2", "daum.net", bitmap2);
        imageIdList.add(item);

        item = new itememainbanner();
        item.setItem( "3", "google.com", bitmap3);
        imageIdList.add(item);

        item = new itememainbanner();
        item.setItem( "4", "m.naver.com", bitmap4);
        imageIdList.add(item);

        BannerDotView.init(getSize(imageIdList), R.drawable.dot_off, R.drawable.dot_on, 15);
        BannerDotView.setSelection(CurrentPosition);


        autoScrollViewPager.setAdapter(new ImagePagerAdapter(this, imageIdList).setInfiniteLoop(true));
        autoScrollViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        autoScrollViewPager.setInterval(5000);
        autoScrollViewPager.startAutoScroll();
        autoScrollViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % getSize(imageIdList));

        //CurrentPosition = 0;
        //BannerDotView.setSelection(CurrentPosition);
    }

    private int getSize(List<itememainbanner> imageIdList){
        int nSize;

        if(imageIdList == null){
            nSize = 0;
        }else {
            nSize = imageIdList.size();
        }
        return nSize;
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

            //Log.e("onPageSelected", "position:" + position);

            CurrentPosition = position % getSize(imageIdList);

            Log.e("onPageSelected", "CurrentPosition:" + CurrentPosition);

            BannerDotView.setSelection(CurrentPosition);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }

    @Override
    protected void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        autoScrollViewPager.stopAutoScroll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start auto scroll when onResume
        autoScrollViewPager.startAutoScroll();
    }

}
