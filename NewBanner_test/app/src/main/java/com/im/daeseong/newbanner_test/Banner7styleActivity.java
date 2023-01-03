package com.im.daeseong.newbanner_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;
import com.im.daeseong.newbanner_test.Banner7_style.AutoScrollViewPager;
import com.im.daeseong.newbanner_test.Banner7_style.ImagePagerAdapter;
import com.im.daeseong.newbanner_test.Banner7_style.ViewPagerIndicatorView;

public class Banner7styleActivity extends AppCompatActivity {

    private static final String TAG = Banner7styleActivity.class.getSimpleName();

    private AutoScrollViewPager banner1;
    private ImagePagerAdapter imagePagerAdapter;
    private ViewPagerIndicatorView indicator1, indicator2;
    private int nTotalCount = 4;
    private int CurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner7style);

        banner1 = (AutoScrollViewPager)findViewById(R.id.banner1);

        indicator1 = (ViewPagerIndicatorView)findViewById(R.id.indicator1);
        indicator2 = (ViewPagerIndicatorView)findViewById(R.id.indicator2);

        indicator1.init(nTotalCount, R.drawable.dot_off, R.drawable.select_on, 15);
        indicator1.setSelection(CurrentPosition);

        indicator2.init(nTotalCount, R.drawable.dot_off, R.drawable.select_on, 15);
        indicator2.setSelection(CurrentPosition);

        imagePagerAdapter = new ImagePagerAdapter(this, nTotalCount).setInfiniteLoop(true);

        //이미지 라운드 처리를 위한 설정
        banner1.setClipToOutline(true);

        banner1.setAdapter(imagePagerAdapter);
        banner1.addOnPageChangeListener(new MyOnPageChangeListener());

        banner1.setInterval(5000);
        banner1.startAutoScroll();
        banner1.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % nTotalCount);


        imagePagerAdapter.setOnItemClickListener(new ImagePagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                try {

                    int nItem = position % nTotalCount;
                    Log.e(TAG, "nItem:" + nItem);

                } catch (Exception ex) {
                    ex.getMessage().toString();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {

            if(nTotalCount > 0) {
                banner1.startAutoScroll();
            }

        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            banner1.stopAutoScroll();

        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

            try {

                CurrentPosition = position % nTotalCount;
                indicator1.setSelection(CurrentPosition);
                indicator2.setSelection(CurrentPosition);

            }catch (Exception ex){
                ex.getMessage().toString();
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }
}