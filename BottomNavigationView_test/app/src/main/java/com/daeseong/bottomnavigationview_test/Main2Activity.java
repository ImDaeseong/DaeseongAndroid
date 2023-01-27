package com.daeseong.bottomnavigationview_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.list).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.sentence).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.word).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.myword).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.setting).setChecked(true);
                        break;
                }
            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager v) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Fragment1());
        viewPagerAdapter.addFragment(new Fragment2());
        viewPagerAdapter.addFragment(new Fragment3());
        viewPagerAdapter.addFragment(new Fragment4());
        viewPagerAdapter.addFragment(new Fragment5());
        v.setAdapter(viewPagerAdapter);
    }
}
