package com.im.daeseong.bannerviewpager_test.Banner_B;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.im.daeseong.bannerviewpager_test.R;

import java.util.ArrayList;

public class BannerView extends RelativeLayout {

    protected RelativeLayout mRootView;
    protected ViewPager mViewPager;
    protected BannerAdapter mAdapter;

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
        mRootView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.widget_banner_b_view, this, true);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.vp);
        initViewPager();
    }

    protected void initViewPager() {
        mAdapter = new BannerAdapter(getContext(), new int[]{});
        mViewPager.setAdapter(mAdapter);
    }

    public void setBannerData(int[] bannerData) {
        mAdapter.setData(bannerData);
    }

    public void setSelectPageIndex(int nPage){
        mViewPager.setCurrentItem(nPage);
    }

}
