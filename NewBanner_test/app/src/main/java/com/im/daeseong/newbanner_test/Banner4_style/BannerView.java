package com.im.daeseong.newbanner_test.Banner4_style;

import android.content.Context;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.im.daeseong.newbanner_test.R;

public class BannerView extends RelativeLayout {

    private static final String TAG = BannerView.class.getSimpleName();

    protected ViewPager mViewPager;
    protected BannerAdapter mAdapter;

    private int CurrentPosition;
    private ViewPagerIndicatorView BannerDotView;
    private Context context;
    private final int SCROLL_TIME = 400;

    public BannerView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(getContext()).inflate(R.layout.banner4_style_view, this);
        mViewPager = (ViewPager)findViewById(R.id.banner4style_widget);
        BannerDotView = (ViewPagerIndicatorView) findViewById(R.id.indicator);
        initViewPager();

        mViewPager = (ViewPager) findViewById(R.id.banner4style_widget);
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    protected void initViewPager() {
        mAdapter = new BannerAdapter(getContext(), new int[]{});
        mViewPager.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BannerAdapter.OnItemClickListener() {
            @Override public void onItemClick(int position) {
                Log.e(TAG, "onItemClick:" + position);
            }
        });
    }

    public void setBannerData(int[] bannerData) {

        mAdapter.setData(bannerData);

        if(mAdapter.getCount() > 1){
            mViewPager.setCurrentItem(0, false);
            BannerDotView.init(mAdapter.getCount(), R.drawable.dot_off, R.drawable.dot_on, 15);
            BannerDotView.setSelection(0);
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            CurrentPosition = position % mAdapter.getCount();
            BannerDotView.setSelection(CurrentPosition);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}