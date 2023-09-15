package com.im.daeseong.mainui_test;

import android.content.Context;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import androidx.fragment.app.FragmentManager;//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;//import android.support.v4.app.FragmentStatePagerAdapter;

public class Tab2PagerAdapter extends FragmentStatePagerAdapter  {

    private final static int TAB_COUNT = 4;     // 탭의 개수

    private int[] tabTitle = {R.string.Tab1, R.string.Tab2, R.string.Tab3, R.string.Tab4};

    private Context mContext;

    private ui2_1Fragment F1ragment = null;
    private ui2_2Fragment F2ragment = null;
    private ui2_3Fragment F3ragment = null;
    private ui2_4Fragment F4ragment = null;

    public Tab2PagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                F1ragment = ui2_1Fragment.newInstance();
                return F1ragment;
            case 1:
                F2ragment = ui2_2Fragment.newInstance();
                return F2ragment;
            case 2:
                F3ragment = ui2_3Fragment.newInstance();
                return F3ragment;
            case 3:
                F4ragment = ui2_4Fragment.newInstance();
                return F4ragment;
            default:
                F1ragment = ui2_1Fragment.newInstance();
                return F1ragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(tabTitle[position]);
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
