package com.daeseong.bottomnavigationview_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        viewPager = findViewById(R.id.viewPager);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.list:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.sentence:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.word:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.myword:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.setting:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(Fragment1.newInstance());// new Fragment1());
        viewPagerAdapter.addFragment(Fragment2.newInstance());// new Fragment2());
        viewPagerAdapter.addFragment(Fragment3.newInstance());// new Fragment3());
        viewPagerAdapter.addFragment(Fragment4.newInstance());// new Fragment4());
        viewPagerAdapter.addFragment(Fragment5.newInstance());// new Fragment5());
        v.setAdapter(viewPagerAdapter);
    }
}