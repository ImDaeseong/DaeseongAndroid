package com.im.daeseong.mainui_test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;

public class Page8Adapter extends FragmentStateAdapter {

    private final List<Fragment> tabList = new ArrayList<>();
    private final List<String> tabTitle = new ArrayList<>();

    public Page8Adapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return tabList.get(position);
    }

    @Override
    public int getItemCount() {
        return tabList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        tabList.add(fragment);
        tabTitle.add(title);
    }

    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}
