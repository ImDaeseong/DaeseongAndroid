package com.im.daeseong.newbanner_test.Banner7_style;

import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.im.daeseong.newbanner_test.OnSingleClickListener;
import com.im.daeseong.newbanner_test.R;

public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private static final String TAG = ImagePagerAdapter.class.getSimpleName();

    private OnItemClickListener listener;

    private Context       context;
    private List<Bitmap> imageIdList;

    private int           size;
    private boolean      isInfiniteLoop;

    private int[] imgs = new int[]{R.drawable.number1,R.drawable.number2,R.drawable.number3,R.drawable.number4};

    public ImagePagerAdapter(Context context, int size) {
        this.context = context;
        this.size = size;
        isInfiniteLoop = false;
    }

    private int getSize(List<Bitmap> imageIdList){
        return this.size;
    }

    @Override
    public int getCount() {
        return isInfiniteLoop ? Integer.MAX_VALUE : getSize(imageIdList);
    }

    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);

            //화면에 맞게 full 로 채운다.
            holder.imageView.setAdjustViewBounds(true);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            view.setTag(0xffffffff, holder);

        } else {
            holder = (ViewHolder)view.getTag(0xffffffff);
        }

        try {

            int nPos = getPosition(position);
            //holder.imageView.setImageResource(imgs[nPos]);

            String sUrl = "";
            if (nPos == 0) {

                sUrl = "https://.png";

            } else if(nPos == 1) {

                sUrl = "https://.png";

            } else if(nPos == 2) {

                sUrl = "https://.jpg";

            } else if(nPos == 3) {

                sUrl = "https://.png";
            }

            Glide.with(context)
                    .load(sUrl)
                    .into(holder.imageView);

        } catch (Exception ex) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.number1));
        }

        holder.imageView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (listener != null)
                    listener.onItemClick(position);
            }
        });

        return view;
    }

    private static class ViewHolder {
        ImageView imageView;
    }

    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
