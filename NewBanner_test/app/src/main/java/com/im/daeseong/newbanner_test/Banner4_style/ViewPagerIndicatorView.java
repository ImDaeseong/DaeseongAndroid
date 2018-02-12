package com.im.daeseong.newbanner_test.Banner4_style;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Daeseong on 2018-02-10.
 */

public class ViewPagerIndicatorView extends LinearLayout {
    private Context mContext;
    private ImageView[] dotImages;
    private int mDefaultDot;
    private int mSelectedDot;

    public ViewPagerIndicatorView(Context context) {
        super(context);

        mContext = context;
    }

    public ViewPagerIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
    }

    public void init(int count, int defaultDot, int selectedDot, int margin) {
        this.removeAllViews();

        dotImages = new ImageView[count];
        mDefaultDot = defaultDot;
        mSelectedDot = selectedDot;

        for (int i=0;i<count;i++) {
            dotImages[i] = new ImageView(mContext);

           LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            params.topMargin = 0;
            params.bottomMargin = 0;
            params.leftMargin = 0;
            if (i == count - 1) params.rightMargin = 0;
            else params.rightMargin = margin;
            params.gravity = Gravity.CENTER;

            dotImages[i].setLayoutParams(params);
            dotImages[i].setImageResource(defaultDot);
            dotImages[i].setTag(dotImages[i].getId(), false);
            this.addView(dotImages[i]);
        }
        setSelection(0);
    }

    public void setSelection(int position) {
        for (int i=0;i<dotImages.length;i++) {
            if (i == position) {
                dotImages[i].setImageResource(mSelectedDot);
            } else {
                dotImages[i].setImageResource(mDefaultDot);
            }
        }
    }
}