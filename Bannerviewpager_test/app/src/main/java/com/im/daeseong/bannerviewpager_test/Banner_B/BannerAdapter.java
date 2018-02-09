package com.im.daeseong.bannerviewpager_test.Banner_B;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private final String TAG = getClass().getSimpleName();
    private List<ImageView> mBannerImgs;
    private int[] mBannerResIds;
    private Context mContext;

    public BannerAdapter(Context context, int [] imgRes) {
        this.mContext = context;
        this.mBannerResIds = imgRes;
        initImageView(mContext);
    }

    public void setData(int[] resIds) {
        mBannerResIds = resIds;
        initImageView(mContext);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBannerResIds.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "--> instantiateItem() [position]: "+position);
        if (mBannerImgs != null && !mBannerImgs.isEmpty()) {
            ImageView iv = mBannerImgs.get(position);
            iv.setImageResource(mBannerResIds[position]);
            container.addView(iv);
            return iv;
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.d(TAG, "--> destroyItem() [position]: "+position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private void initImageView(Context context) {
        mBannerImgs = new ArrayList<>();
        for (int i : mBannerResIds) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(i);
            mBannerImgs.add(iv);
        }
    }
}
