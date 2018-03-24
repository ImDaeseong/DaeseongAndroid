package com.im.daeseong.newbanner_test.Banner6_style;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BannerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{

    private final String TAG = getClass().getSimpleName();

    private int[] mBannerResIds;
    private Context mContext;
    private OnItemClickListener listener;

    public BannerAdapter(Context context, int [] imgRes) {
        this.mContext = context;
        this.mBannerResIds = imgRes;
    }

    @Override
    public int getCount() {
        return Short.MAX_VALUE;//return Integer.MAX_VALUE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        ImageView iv = new ImageView(mContext);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setImageResource(mBannerResIds[position%mBannerResIds.length]);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(position);
            }
        });

        container.addView(iv);

        return iv;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
