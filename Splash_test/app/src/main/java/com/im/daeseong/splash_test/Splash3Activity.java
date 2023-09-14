package com.im.daeseong.splash_test;

import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;//import android.support.constraint.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash3Activity extends AppCompatActivity {

    private static final String TAG = Splash3Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);
        StartAnimation();

        Thread thread = new Thread(){
            @Override
            public void run() {
                try{

                    Log.e(TAG, "run");
                    sleep(5000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(Splash3Activity.this, MainActivity.class));
                }
                finish();
            }
        };
        thread.start();
    }

    private void StartAnimation(){

        Log.e(TAG, "StartAnimation");

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        ConstraintLayout l=(ConstraintLayout) findViewById(R.id.lin);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.clearAnimation();
        imageView2.startAnimation(anim);
    }

}
