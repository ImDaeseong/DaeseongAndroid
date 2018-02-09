package com.im.daeseong.bannerviewpager_test.Banner_C;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import java.lang.reflect.Field;
import com.im.daeseong.bannerviewpager_test.R;

public class BannerView {
    private Context context;
    private ViewPager viewPager;
    public PagerAdapter pagerAdapter;

    private final int DELAY_TIME = 4000;

    private final int SCROLL_TIME = 400;
    public LinearLayout linearLayout;
    private View bannerView;
    private int[] pics;
    private final int SCROLL_WHAT = 0;
    private LinearLayout.LayoutParams params1,params2;
    private View moveDot;

    private int dotDistance;

    private int dotDiameter;

    private int dotSpace;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(SCROLL_WHAT,DELAY_TIME);
        }
    };

    public BannerView(Context context,int[] pics,PagerAdapter pagerAdapter,int layout){
        this.context = context;
        this.pics = pics;
        this.pagerAdapter = pagerAdapter;
        bannerView = LayoutInflater.from(context).inflate(layout,null);
        initView();
        event();
    }

    private void initView() {
        linearLayout = bannerView.findViewById(R.id.dot_linear);
        viewPager = bannerView.findViewById(R.id.viewpager);
        moveDot = bannerView.findViewById(R.id.movedot);

        viewPager.setCurrentItem(pics.length*Integer.MAX_VALUE/2);
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        handler.sendEmptyMessageDelayed(SCROLL_WHAT, DELAY_TIME);

        dotSpace = dp2px(7);
        dotDiameter = dp2px(6);
        dotDistance = dotDiameter + dotSpace;

        params1 = new LinearLayout.LayoutParams(dotDiameter,dotDiameter);
        params1.leftMargin = 0;
        params2 = new LinearLayout.LayoutParams(dotDiameter,dotDiameter);
        params2.leftMargin = dotSpace;

        setViewPagerDuration();
        initDot();
    }

    private void event(){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) moveDot.getLayoutParams();

                int dotPos = position % pics.length;
                params.leftMargin = (int) (dotDistance * dotPos + dotDistance * positionOffset);
                moveDot.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void initDot(){
        View dot;
        for(int i=0;i<pics.length;i++){
            dot = new View(context);
            if(i == 0){
                dot.setLayoutParams(params1);
                dot.setBackgroundResource(R.drawable.dot_unfocus);
            }else{
                dot.setLayoutParams(params2);
                dot.setBackgroundResource(R.drawable.dot_unfocus);
            }
            linearLayout.addView(dot);
        }
    }

    private void setViewPagerDuration(){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(viewPager,getScroller(SCROLL_TIME));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Scroller getScroller(final int smoothDuration){
        Scroller scroller = new Scroller(context,new AccelerateInterpolator()){
            @Override
            public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                super.startScroll(startX, startY, dx, dy, smoothDuration);
            }
        };
        return scroller;
    }

    public View getBannerView(){
        return bannerView;
    }

    private int dp2px(int dp){
        return (int) (context.getResources().getDisplayMetrics().density*dp + 0.5);
    }

}