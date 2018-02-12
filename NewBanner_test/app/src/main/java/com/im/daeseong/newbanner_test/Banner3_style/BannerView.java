package com.im.daeseong.newbanner_test.Banner3_style;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.im.daeseong.newbanner_test.R;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * Created by Daeseong on 2018-02-10.
 */

public class BannerView extends RelativeLayout {

    private Context context;
    private ViewPager BannerPager;
    private BannerAdapter adapter;
    private DotView BannerDotView;

    private ArrayList<Banner> banners;
    private int position;


    private final int DELAY_TIME = 5000;
    private final int SCROLL_WHAT = 0;
    private final int SCROLL_TIME = 400;

    private BannerItemClickListener listener;

    public BannerView(Context context){
        super(context);
        this.context = context;
        initView();
    }

    public BannerView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.context = context;
        initView();
    }

    public BannerView(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context, attributeSet, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.banner3_style_view, this);
        BannerPager = (ViewPager) findViewById(R.id.banner3style_widget);
        BannerPager.addOnPageChangeListener(onPageChangeListener);
        setViewPagerDuration();
        BannerDotView = (DotView) findViewById(R.id.banner3style_DotView);
    }

    public void setUpData(ArrayList<Banner> banners, BannerItemClickListener listener) {
        this.banners = banners;
        this.listener = listener;
        adapter = new BannerAdapter();
        BannerPager.setAdapter(adapter);
        int half = Short.MAX_VALUE / 2;
        half = half - half % banners.size();
        BannerPager.setCurrentItem(half);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            BannerPager.setCurrentItem(BannerPager.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(SCROLL_WHAT,DELAY_TIME);
            /*
            if (position < Short.MAX_VALUE - 1){
                BannerPager.setCurrentItem(position + 1, true);
                handler.sendEmptyMessageDelayed(SCROLL_WHAT, DELAY_TIME);
            }
            */
        }
    };

    public void onStart(){
        handler.removeMessages(0);
        handler.sendEmptyMessageDelayed(SCROLL_WHAT, DELAY_TIME);
    }

    public void onStop(){
        handler.removeMessages(0);
    }

    private class BannerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Short.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            final int index = position % banners.size();
            final Banner banner = banners.get(index);
            final ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(banner.resId);
            /*
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBannerClick(index, banner);
                }
            });
            */
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            handler.removeMessages(0);
            BannerDotView.notifyDataChanged(position % banners.size(), banners.size());
            handler.sendEmptyMessageDelayed(SCROLL_WHAT, DELAY_TIME);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    public interface BannerItemClickListener {
        void onClink(String url);
    }

    private void setViewPagerDuration(){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(BannerPager,getScroller(SCROLL_TIME));
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

    public static class Banner {
        public int resId;
        public String title;

        public Banner(int resId, String title ) {
            this.resId = resId;
            this.title = title;
        }
    }

}
