package com.im.daeseong.mainui_test;

import com.google.android.material.appbar.CollapsingToolbarLayout;//import android.support.design.widget.CollapsingToolbarLayout;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;//import android.support.v7.widget.Toolbar;
import android.os.Bundle;

public class Mainui9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui9);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("toolbar title");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle("CollapsingToolbarLayout title");
    }
}
