package com.daeseong.bottomnavigationview_test;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

public class Main7Activity extends AppCompatActivity {

    private static final String TAG = Main7Activity.class.getSimpleName();

    private BottomNavigationView bottomNavigationView;
    private NoScrollViewPager viewPager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        viewPager = findViewById(R.id.viewPager);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int position = -1;
            if (item.getItemId() == R.id.list) {
                position = 0;
            } else if (item.getItemId() == R.id.sentence) {
                position = 1;
            } else if (item.getItemId() == R.id.word) {
                position = 2;
            } else if (item.getItemId() == R.id.myword) {
                position = 3;
            } else if (item.getItemId() == R.id.setting) {
                position = 4;
            }

            if (position != -1) {
                viewPager.setCurrentItem(position, false);
                return true;
            }
            return false;
        });

        viewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);
            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager v) {
        ViewPagerAdapter7 viewPagerAdapter = new ViewPagerAdapter7(this.getSupportFragmentManager());
        viewPagerAdapter.addFragment(Fragment1.newInstance());
        viewPagerAdapter.addFragment(Fragment2.newInstance());
        viewPagerAdapter.addFragment(Fragment3.newInstance());
        viewPagerAdapter.addFragment(Fragment4.newInstance());
        viewPagerAdapter.addFragment(Fragment5.newInstance());
        v.setAdapter(viewPagerAdapter);
    }
}
