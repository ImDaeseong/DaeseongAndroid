package com.im.daeseong.animationutils_test;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        iv1 = (ImageView)findViewById(R.id.iv1);
        iv1.animate().alpha(1).rotation(360).setDuration(1000);
        iv1.animate().scaleX(0.8f).scaleY(0.8f).setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);

                Main4Activity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 2000);

    }
}
