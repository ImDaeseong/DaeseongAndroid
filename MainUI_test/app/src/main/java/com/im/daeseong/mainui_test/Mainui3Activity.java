package com.im.daeseong.mainui_test;

import android.app.Activity;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import androidx.fragment.app.FragmentManager;//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;//import android.support.v4.app.FragmentPagerAdapter;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;//import android.support.v7.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;//import android.support.v7.widget.Toolbar;
import androidx.core.view.GravityCompat;//import android.support.v4.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;//import android.support.v4.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;//import android.support.design.widget.NavigationView;
import com.google.android.material.tabs.TabLayout;//import android.support.design.widget.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class Mainui3Activity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    private NavigationView mnavigationView;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui3);

        backPressCloseHandler = new BackPressCloseHandler(this);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mdrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        mnavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mnavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mdrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        mviewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mviewPager);

        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        mtabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mtabLayout.setupWithViewPager(mviewPager);

        setupTabIcons();
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("탭1");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.recipe, 0, 0);
        mtabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("탭2");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.recipe, 0, 0);
        mtabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("탭3");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.recipe, 0, 0);
        mtabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("탭4");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.recipe, 0, 0);
        mtabLayout.getTabAt(3).setCustomView(tabFour);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ui2_1Fragment(), "탭1");
        adapter.addFrag(new ui2_2Fragment(), "탭2");
        adapter.addFrag(new ui2_3Fragment(), "탭3");
        adapter.addFrag(new ui2_4Fragment(), "탭4");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        backPressCloseHandler.onBackPressed();
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }
    }

    public class BackPressCloseHandler {
        private long backKeyPressedTime = 0;
        private Toast toast;
        private Activity activity;

        public BackPressCloseHandler(Activity context) {
            this.activity = context;
        }

        public void onBackPressed() {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showGuide();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                activity.finish();
                toast.cancel();
            }
        }

        public void showGuide() {
            toast = Toast.makeText(activity, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
