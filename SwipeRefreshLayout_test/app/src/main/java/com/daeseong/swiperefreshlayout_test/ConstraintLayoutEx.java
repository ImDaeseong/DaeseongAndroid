package com.daeseong.swiperefreshlayout_test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintLayoutEx extends ConstraintLayout {

    private ImageView iv1;
    private Animation loading;

    public ConstraintLayoutEx(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ConstraintLayoutEx(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ConstraintLayoutEx(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.wait, this);

        iv1 = (ImageView)view.findViewById(R.id.iv1);

        loading = AnimationUtils.loadAnimation(context, R.anim.loading);
        //iv1.startAnimation(loading);
    }

    public void show(){

        setVisibility(View.VISIBLE);
        iv1.startAnimation(loading);
    }

    public void hide(){

        setVisibility(View.GONE);
        clearAnimation();
        iv1.clearAnimation();
    }
}
