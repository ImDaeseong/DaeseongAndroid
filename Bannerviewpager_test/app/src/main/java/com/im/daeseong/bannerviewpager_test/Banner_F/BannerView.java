package com.im.daeseong.bannerviewpager_test.Banner_F;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.im.daeseong.bannerviewpager_test.R;

public class BannerView extends RelativeLayout {

    private final String TAG = BannerView.class.getSimpleName();

    private ViewPager viewPager;
    private BannerAdapter adapter;
    private int[] pics;
    private ClickListener listener;
    private int realPosition;

    public BannerView(Context context) {
        super(context);
        initView();
    }

    public BannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        View view = inflate(getContext(), R.layout.widget_banner_f_view, null);
        addView(view);

        final GestureDetector tapGestureDetector = new GestureDetector(getContext(), new TapGestureListener());
        viewPager = (ViewPager) findViewById(R.id.vpBanner);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    public void setBannerView(int[] pics, ClickListener listener) {
        this.pics = pics;
        this.listener = listener;

        adapter = new BannerAdapter(getContext(), pics);
        viewPager.setAdapter(adapter);
        if(pics.length > 1) {
            viewPager.setCurrentItem(0, false);
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

            realPosition = position % adapter.getRealCount();
            Log.d(TAG, "onPageSelected position : " + position + ", currentPosition : " + realPosition);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private class TapGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            if(listener != null) {
                int nClick = realPosition % pics.length;
                listener.onClink(String.valueOf("nClick:" + nClick));
            }
            return false;
        }
    }

    public interface ClickListener {
        void onClink(String url);
    }
}
