package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

public class Animate5Activity extends AppCompatActivity {

    private static final String TAG = Animate5Activity.class.getSimpleName();

    private Button button1;

    private ProgressBar progressbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate5);

        progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation1(10);
            }
        });

    }

    private void runAnimation1(int nPos) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(progressbar1, "progress", 0, nPos * 10);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Drawable progressDrawable = progressbar1.getProgressDrawable().mutate();
                int nColor = (int) animation.getAnimatedValue();
                Log.e(TAG, "nColor:" + nColor/10);
                //progressDrawable.setColorFilter(getResources().getColor(R.color.progressbar), PorterDuff.Mode.SRC_IN);
                //progressbar1.setProgressDrawable(progressDrawable);
            }
        });

    }

}
