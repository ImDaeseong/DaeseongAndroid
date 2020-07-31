package com.daeseong.imageviewgradient_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Main8Activity extends AppCompatActivity {

    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        image1 = findViewById(R.id.image1);

        //전체 투명도(80%)
        image1.setAlpha(0.8f);

    }

}
