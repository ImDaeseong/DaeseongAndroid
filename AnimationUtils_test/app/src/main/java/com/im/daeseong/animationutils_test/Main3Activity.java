package com.im.daeseong.animationutils_test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {

    private AppCompatDialog appCompatDialog;
    private ImageView iv1;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        }, 5000);
        

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);

                Main3Activity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        },2000);
    }

    private void show(){

        appCompatDialog = new AppCompatDialog(this);
        appCompatDialog.setCancelable(false);
        appCompatDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        appCompatDialog.setContentView(R.layout.loading_layout);
        appCompatDialog.show();

        iv1 = (ImageView)appCompatDialog.findViewById(R.id.iv1);
        final AnimationDrawable animationDrawable = (AnimationDrawable)iv1.getBackground();
        iv1.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        });
    }

    private void hide(){
        if(appCompatDialog != null){
            appCompatDialog.dismiss();
        }
    }

}
