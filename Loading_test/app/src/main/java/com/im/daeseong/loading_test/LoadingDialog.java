package com.im.daeseong.loading_test;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoadingDialog extends Dialog {

    private Animation animation;
    private ImageView iv1;

    public LoadingDialog(Context context){
        super(context);

        setCancelable(false);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loadingdialog);

        iv1 = (ImageView)findViewById(R.id.iv1);
    }

    @Override
    public void show() {
        super.show();

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.loading);
        iv1.startAnimation(animation);
    }

    @Override
    public void dismiss() {
        super.dismiss();

        iv1.clearAnimation();
        animation = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
