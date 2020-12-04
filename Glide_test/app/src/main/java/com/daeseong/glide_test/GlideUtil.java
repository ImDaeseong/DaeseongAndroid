package com.daeseong.glide_test;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {

    private static final String TAG = GlideUtil.class.getSimpleName();

    private static GlideUtil glideUtil;
    public static GlideUtil getInstance() {

        if (glideUtil == null) {
            glideUtil = new GlideUtil();
        }
        return glideUtil;
    }

    private GlideUtil() {
    }

    public void load(Context context, ImageView imageView, String sUrl) {

        Glide.with(context)
                .load(sUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background))
                .into(imageView);
    }
}
