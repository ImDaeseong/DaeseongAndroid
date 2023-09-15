package com.im.daeseong.uidrawer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;//import android.support.v7.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.core.view.GravityCompat;//import android.support.v4.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;//import android.support.v4.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import com.google.android.material.navigation.NavigationView;//import android.support.design.widget.NavigationView;
import com.google.android.material.tabs.TabLayout;//import android.support.design.widget.TabLayout;

public class Main2Activity extends AppCompatActivity {

    private DrawerLayout Main_drawerLayout;
    private ImageView Main_picabi, Main_picareser, Main_picamenu;
    private TabLayout Main_tabLayout;
    private NavigationView Main_navigationView;
    private ViewPager Main_viewPager;
    private MainPagerAdapter mainPagerAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Main_drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        Main_tabLayout = (TabLayout)findViewById(R.id.maintabLayout);

        Main_picabi = (ImageView)findViewById(R.id.picabi);
        Main_picareser = (ImageView)findViewById(R.id.picareser);
        Main_picamenu = (ImageView)findViewById(R.id.picamenu);

        Main_viewPager = (ViewPager)findViewById(R.id.mainviewPager);

        Main_navigationView = (NavigationView)findViewById(R.id.mainnav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, Main_drawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        Main_drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        Main_navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                Main_drawerLayout.closeDrawer(GravityCompat.END);
                return true;
            }
        });


        Main_picabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "picabi");
            }
        });

        Main_picareser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "picareser");
            }
        });

        Main_picamenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (Main_drawerLayout.isDrawerOpen(GravityCompat.END)) {
                        Main_drawerLayout.closeDrawer(GravityCompat.END);
                    } else {
                        Main_drawerLayout.openDrawer(GravityCompat.END);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        setViewPager();
        setInitTabLayout();
    }

    @Override
    public void onBackPressed() {

        if (Main_drawerLayout.isDrawerOpen(GravityCompat.END)) {
            Main_drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    private void setInitTabLayout(){

        Main_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.e("tag", "tab.getPosition():" + tab.getPosition());

                switch(tab.getPosition()){
                    case 0:
                        //toolbar.inflateMenu(R.menu.menu_two);
                        //toolbar.setTitle(TollBarTitle[0]);
                        break;
                    case 1:
                        //toolbar.inflateMenu(R.menu.menu_two);
                        //toolbar.setTitle(TollBarTitle[1]);
                        break;
                    case 2:
                        //toolbar.inflateMenu(R.menu.menu_three);
                        //toolbar.setTitle(TollBarTitle[2]);
                        break;
                    case 3:
                        //toolbar.inflateMenu(R.menu.menu_three);
                        //toolbar.setTitle(TollBarTitle[3]);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setViewPager(){

        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        Main_viewPager.setAdapter(mainPagerAdapter);

        //Main_viewPager.setOffscreenPageLimit(4);
        Main_tabLayout.setupWithViewPager(Main_viewPager);
    }

}
