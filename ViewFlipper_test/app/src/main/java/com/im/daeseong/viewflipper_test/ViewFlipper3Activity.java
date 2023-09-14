package com.im.daeseong.viewflipper_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class ViewFlipper3Activity extends AppCompatActivity {

    private static final String TAG = ViewFlipper3Activity.class.getSimpleName();

    private ViewFlipper viewFlipper1;
    private Animation Fade_in, Fade_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper3);

        setTitle(TAG);

        viewFlipper1 = (ViewFlipper)findViewById(R.id.viewFlipper1);

        Fade_in= AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        Fade_out=AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        viewFlipper1.setInAnimation(Fade_in);
        viewFlipper1.setOutAnimation(Fade_out);

        viewFlipper1.setAutoStart(true);
        viewFlipper1.setFlipInterval(5000);
        viewFlipper1.startFlipping();
    }
}
