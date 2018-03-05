package com.im.daeseong.uidrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Daeseong on 2018-03-02.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 4;
    private String titles[] = {"텝1", "텝2", "텝3", "텝4"};

    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new MainTab1Fragment();
            case 1: return new MainTab2Fragment();
            case 2: return new MainTab3Fragment();
            case 3: return new MainTab4Fragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
