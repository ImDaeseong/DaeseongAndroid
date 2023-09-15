package com.im.daeseong.mainui_test;

import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;//import android.support.v7.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;//import android.support.v7.widget.Toolbar;
import androidx.core.view.GravityCompat;//import android.support.v4.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;//import android.support.v4.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Mainui2Activity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout = null;
    private TabLayout mtabLayout = null;
    private ViewPager mviewPager = null;
    private  Tab2PagerAdapter mtab2PagerAdapter = null;

    private int[] tabIcons = {
            R.drawable.b1,
            R.drawable.b2,
            R.drawable.b3,
            R.drawable.b4
    };

    private int[] tabTexts = {
            R.string.Tab1,
            R.string.Tab2,
            R.string.Tab3,
            R.string.Tab4
    } ;

    private int[] tabIconsSelect = {
            R.drawable.b1,
            R.drawable.b2,
            R.drawable.b3,
            R.drawable.b4
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui2);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 타이틀에 있는 앱이름 숨기기

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mdrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationViewleft = (NavigationView)findViewById(R.id.navigation_view);
        navigationViewleft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mdrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        mtabLayout = (TabLayout)findViewById(R.id.tablayout);
        mviewPager = (ViewPager)findViewById(R.id.viewpager);
        mtabLayout.setupWithViewPager(mviewPager);

        mtab2PagerAdapter = new Tab2PagerAdapter(getSupportFragmentManager(), this);
        mviewPager.setAdapter(mtab2PagerAdapter);
        mviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtabLayout));
        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mviewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
