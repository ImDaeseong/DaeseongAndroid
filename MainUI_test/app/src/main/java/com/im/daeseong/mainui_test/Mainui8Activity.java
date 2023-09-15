package com.im.daeseong.mainui_test;

import com.google.android.material.tabs.TabLayout;//import android.support.design.widget.TabLayout;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;//import android.support.v7.widget.Toolbar;
import android.os.Bundle;

public class Mainui8Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui8);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPage(viewPager);

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPage(ViewPager viewPager){
        Page8Adapter adapter =  new Page8Adapter(getSupportFragmentManager());
        adapter.addFragment(new ui6_1Fragment(), "tab1");
        adapter.addFragment(new ui6_2Fragment(), "tab2");
        adapter.addFragment(new ui6_3Fragment(), "tab3");
        adapter.addFragment(new ui6_4Fragment(), "tab4");
        viewPager.setAdapter(adapter);
    }

}
