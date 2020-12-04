package com.daeseong.glide_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView)findViewById(R.id.iv1);

        GlideUtil.getInstance().load(this, iv1, "http://goo.gl/gEgYUd");

        /*
        Glide.with(this)
                .load("http://goo.gl/gEgYUd")
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background))
                .into(iv1);
        */
    }
}
