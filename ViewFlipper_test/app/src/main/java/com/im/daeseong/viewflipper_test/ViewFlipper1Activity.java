package com.im.daeseong.viewflipper_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ViewFlipper1Activity extends AppCompatActivity {

    private static final String TAG = ViewFlipper1Activity.class.getSimpleName();

    private Button button1, button2;
    private ImageView imageView1, imageView2, imageView3;
    private ViewFlipper viewFlipper1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper1);

        setTitle(TAG);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        viewFlipper1 = (ViewFlipper)findViewById(R.id.viewFlipper1);

        //int childID = viewFlipper1.getDisplayedChild();
        //int childCount = viewFlipper1.getChildCount();
        //Log.e(TAG, "childID:" + String.valueOf(childID) + " childCount:" + String.valueOf(childCount) );

        viewFlipper1.setInAnimation(AnimationUtils.loadAnimation(ViewFlipper1Activity.this, android.R.anim.slide_in_left));
        viewFlipper1.setOutAnimation(AnimationUtils.loadAnimation(ViewFlipper1Activity.this, android.R.anim.slide_out_right));


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper1.startFlipping();
                viewFlipper1.setFlipInterval(2000);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper1.stopFlipping();
            }
        });

        viewFlipper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childID = viewFlipper1.getDisplayedChild();
                int childCount = viewFlipper1.getChildCount();

                Log.e(TAG, "childID:" + String.valueOf(childID) + " childCount:" + String.valueOf(childCount) );

                if(childCount == childID + 1){
                    viewFlipper1.setDisplayedChild(0);
                }else{
                    viewFlipper1.setDisplayedChild(childID+1);
                }
            }
        });

    }
}
