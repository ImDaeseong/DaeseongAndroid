package com.im.daeseong.mainui_test;

import android.content.Context;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import androidx.fragment.app.FragmentManager;//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;//import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daeseong on 2018-02-26.
 */

public class Page8Adapter extends FragmentStatePagerAdapter {

    private final List<Fragment> tabList = new ArrayList<>();
    private final List<String> tabTitle = new ArrayList<>();

    public Page8Adapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return tabList.get(position);
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    public void addFragment(Fragment fragment, String title){
        tabList.add(fragment);
        tabTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitle.get(position);
    }

}
