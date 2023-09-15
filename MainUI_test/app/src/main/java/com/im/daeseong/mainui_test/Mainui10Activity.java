package com.im.daeseong.mainui_test;

import androidx.appcompat.app.ActionBar;//import android.support.v7.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.tabs.TabLayout;

public class Mainui10Activity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private View mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui10);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar10_main);

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.container);

        setupViewPage(viewPager);

        tabs.setupWithViewPager(viewPager);
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
