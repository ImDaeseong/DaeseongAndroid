package com.im.daeseong.newbanner_test.Banner3_style;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.im.daeseong.newbanner_test.R;
import java.lang.reflect.Field;


public class BannerView extends RelativeLayout {

    private static final String TAG = BannerView.class.getSimpleName();

    protected ViewPager mViewPager;
    protected BannerAdapter mAdapter;

    private ClickListener listener;
    private int CurrentPosition = 0;
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
        LayoutInflater.from(getContext()).inflate(R.layout.banner3_style_view, this);
        mViewPager = (ViewPager)findViewById(R.id.banner3style_widget);
        BannerDotView = (ViewPagerIndicatorView) findViewById(R.id.indicator);
        initViewPager();

        final GestureDetector tapGestureDetector = new GestureDetector(getContext(), new TapGestureListener());
        mViewPager = (ViewPager) findViewById(R.id.banner3style_widget);
        mViewPager.addOnPageChangeListener(onPageChangeListener);
        setViewPagerDuration();
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    protected void initViewPager() {
        mAdapter = new BannerAdapter(getContext(), new int[]{});
        mViewPager.setAdapter(mAdapter);
    }

    public void setBannerData(int[] bannerData, ClickListener listener) {

        mAdapter.setData(bannerData);

        this.listener = listener;

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

    private class TapGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            if(listener != null) {
                int nClick = CurrentPosition % mAdapter.getCount();
                listener.onClink(String.valueOf("nClick:" + nClick));
            }
            return false;
        }
    }

    private void setViewPagerDuration(){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(mViewPager,getScroller(SCROLL_TIME));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Scroller getScroller(final int smoothDuration){
        Scroller scroller = new Scroller(context, new AccelerateInterpolator()){
            @Override
            public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                super.startScroll(startX, startY, dx, dy, smoothDuration);
            }
        };
        return scroller;
    }

    public interface ClickListener {
        void onClink(String url);
    }

}