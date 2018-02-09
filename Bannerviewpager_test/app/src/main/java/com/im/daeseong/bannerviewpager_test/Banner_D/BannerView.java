package com.im.daeseong.bannerviewpager_test.Banner_D;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.im.daeseong.bannerviewpager_test.R;

public class BannerView extends RelativeLayout implements View.OnClickListener{

    private ImageView banner;

    public BannerView(Context context) {
        super(context);
        initView(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initView(context);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_banner_d_view, this);
        banner = (ImageView) view.findViewById(R.id.banner);
        banner.setOnClickListener(this);
    }

    public void setImage(Bitmap bitmap){
        banner.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
               Log.e("BannerView", "onClick");
        }
    }
}
