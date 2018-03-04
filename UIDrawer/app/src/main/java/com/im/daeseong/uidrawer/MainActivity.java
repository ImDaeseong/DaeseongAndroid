package com.im.daeseong.uidrawer;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout Main_drawerLayout;
    private ImageView Main_picabi, Main_picareser, Main_picamenu;
    private TabLayout Main_tabLayout;
    private NavigationView  Main_navigationView;
    private ViewPager Main_viewPager;
    private MainPagerAdapter mainPagerAdapter = null;

    private ImageView profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(0, 0, 0));
        }

        Main_drawerLayout = (DrawerLayout)findViewById(R.id.main_drawerLayout);

        Main_tabLayout = (TabLayout)findViewById(R.id.maintabLayout);

        //탭 넓이 계산 -- 잘 않맞네
        //Main_tabLayout.post(tabLayoutWidth);

        //Main_tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        //Main_tabLayout.setTabTextColors(Color.parseColor("#707070"), Color.parseColor("#FFFFFF"));
        //Main_tabLayout.getChildAt(0).setBackgroundColor(Color.parseColor("#008000")); // 배경색
        //Main_tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFF00")); // 밑줄색
        //Main_tabLayout.setSelectedTabIndicatorHeight(4*mDensity); // 밑줄높이(두께)


        Main_picabi = (ImageView)findViewById(R.id.picabi);
        Main_picareser = (ImageView)findViewById(R.id.picareser);
        Main_picamenu = (ImageView)findViewById(R.id.picamenu);

        Main_viewPager = (ViewPager)findViewById(R.id.mainviewPager);


        Main_navigationView = (NavigationView)findViewById(R.id.mainnav_view);

        //Drawer Header Menu 접근
        View headerLayout = Main_navigationView.getHeaderView(0);
        profile = (ImageView)headerLayout.findViewById(R.id.imageView1);
        profile.setImageResource(R.drawable.profile1);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "profile");
            }
        });


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

                Log.e("tag", "Main_picamenu");

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

    Runnable tabLayoutWidth = new Runnable() {
        @Override
        public void run() {

            if(Main_tabLayout.getWidth() < MainActivity.this.getResources().getDisplayMetrics().widthPixels ){
                Main_tabLayout.setTabMode(TabLayout.MODE_FIXED);
                ViewGroup.LayoutParams mParams = Main_tabLayout.getLayoutParams();
                mParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                Main_tabLayout.setLayoutParams(mParams);

                Log.e("tag", "tabLayoutWidth:MODE_FIXED");

            }else{
                Main_tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

                Log.e("tag", "tabLayoutWidth:MODE_SCROLLABLE");
            }
        }
    };

    private void setInitTabLayout(){

        //Main_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Main_tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //Main_tabLayout.setPaddingRelative(0, 0, 0, 0);

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

        Main_tabLayout.setupWithViewPager(Main_viewPager);

        Main_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
