package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Animate6Activity extends AppCompatActivity {

    private static final String TAG = Animate6Activity.class.getSimpleName();

    private Button button1, button2;

    private ImageView image1;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate6);

        image1 = findViewById(R.id.image1);

        animationDrawable = (AnimationDrawable)image1.getDrawable();

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(animationDrawable!=null && !animationDrawable.isRunning()){
                    animationDrawable.start();
                }
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(animationDrawable!=null && animationDrawable.isRunning()){
                    animationDrawable.stop();
                }
            }
        });

    }

}
