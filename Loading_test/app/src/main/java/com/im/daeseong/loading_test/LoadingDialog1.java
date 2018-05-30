package com.im.daeseong.loading_test;

import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoadingDialog1 extends Dialog {

    private Animation animation;
    private ImageView iv1;

    public LoadingDialog1(Context context){
        super(context, R.style.TransparentLoadingLayout);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        setContentView(R.layout.loadingdialog1);

        iv1 = (ImageView)findViewById(R.id.iv1);
    }

    @Override
    public void show() {
        super.show();

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
        iv1.setAnimation(animation);
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
