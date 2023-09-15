package com.im.daeseong.uidrawer;

import com.google.android.material.tabs.TabLayout;//import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

public class TabLayout_util {

    private static TabLayout_util tabLayout_util;

    public static TabLayout_util getInstance() {
        if (tabLayout_util == null) {
            tabLayout_util = new TabLayout_util();
        }
        return tabLayout_util;
    }

    public static void dynamicSetTabLayoutMode(TabLayout tabLayout) {

        int nTabWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0);
            nTabWidth += view.getMeasuredWidth();
        }

        int screenWidth = MyApplication.getScreenWidth();

        if ( nTabWidth <= screenWidth ) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);

            Log.e("tag", "MODE_FIXED");
        } else {
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

            Log.e("tag", "MODE_SCROLLABLE");
        }
    }

}
