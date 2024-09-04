package com.daeseong.bottomnavigationview_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main5Activity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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
                }

                return true;
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager2 viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(Fragment1.newInstance());
        viewPagerAdapter.addFragment(Fragment2.newInstance());
        viewPagerAdapter.addFragment(Fragment3.newInstance());
        viewPagerAdapter.addFragment(Fragment4.newInstance());
        viewPagerAdapter.addFragment(Fragment5.newInstance());
        viewPager.setAdapter(viewPagerAdapter);
    }
}
