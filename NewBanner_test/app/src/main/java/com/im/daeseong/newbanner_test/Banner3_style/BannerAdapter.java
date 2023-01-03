package com.im.daeseong.newbanner_test.Banner3_style;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;//import android.support.v4.view.PagerAdapter;
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
    private OnItemClickListener listener;

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
    public Object instantiateItem(ViewGroup container, final int position) {
        if (mBannerImgs != null && !mBannerImgs.isEmpty()) {
            ImageView iv = mBannerImgs.get(position);
            iv.setImageResource(mBannerResIds[position]);

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
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
