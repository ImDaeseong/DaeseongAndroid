package com.im.daeseong.mainui_test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Tab2PagerAdapter extends FragmentStateAdapter {

    private static final int TAB_COUNT = 4;

    public Tab2PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return ui2_2Fragment.newInstance();
            case 2:
                return ui2_3Fragment.newInstance();
            case 3:
                return ui2_4Fragment.newInstance();
            default:
                return ui2_1Fragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
