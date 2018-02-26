package com.im.daeseong.mainui_test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Daeseong on 2018-02-26.
 */

public class ViewPager6FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    public ViewPager6FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
