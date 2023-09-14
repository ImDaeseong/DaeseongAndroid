package com.im.daeseong.viewpager_test;

import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import androidx.fragment.app.FragmentManager;//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;//import android.support.v4.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewPager6Activity extends AppCompatActivity {

    private static final String TAG = ViewPager6Activity.class.getSimpleName();

    private ViewPager viewPager6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager6);

        viewPager6 = (ViewPager)findViewById(R.id.view_pager6);
        viewPager6.setAdapter(new Pagar6Adapter(getSupportFragmentManager()));

    }

    public class Pagar6Adapter extends FragmentPagerAdapter {

        public int [] images = {R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4};

        public Pagar6Adapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            return ItemFragment.newInstance(images[position]);
        }

        @Override
        public int getCount() {

            return images.length;
        }
    }


}
