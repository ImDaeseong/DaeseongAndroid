package com.im.daeseong.mainui_test;

import android.graphics.Color;
import com.google.android.material.appbar.CollapsingToolbarLayout;//import android.support.design.widget.CollapsingToolbarLayout;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;//import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.ImageView;

public class Mainui7Activity extends AppCompatActivity {

    private ImageView iv;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui7);

        mCollapsingToolbarLayout();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setImageResource(R.drawable.b1);

    }

    private void mCollapsingToolbarLayout()
    {
        String title = "title";
        int time = 0;
        if(time>=6&&time<9){
            title = "Morning";
        } else if(time>=9&&time<12){
            title = "Forenoon";
        } else if(time>=12&&time<15){
            title = "Noon";
        } else if(time>=15&&time<19){
            title = "Afternoon";
        } else{
            title = "Evening";
        }
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setEnabled(true);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
    }


}
