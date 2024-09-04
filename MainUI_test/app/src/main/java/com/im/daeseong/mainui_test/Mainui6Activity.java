package com.im.daeseong.mainui_test;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;

public class Mainui6Activity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    private final int[] iconResId = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4};
    private final int[] toolbarTitle = {R.string.Tab1, R.string.Tab2, R.string.Tab3, R.string.Tab4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui6);

        viewPager = findViewById(R.id.myViewPager);
        tabLayout = findViewById(R.id.TabLayout);
        toolbar = findViewById(R.id.ToolBar);
        toolbar.setTitle(toolbarTitle[0]);
        toolbar.setTitleTextColor(Color.YELLOW);
        setSupportActionBar(toolbar);

        setupViewPager();
        setupTabLayout();
    }

    private void setupViewPager() {

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ui6_1Fragment());
        fragmentList.add(new ui6_2Fragment());
        fragmentList.add(new ui6_3Fragment());
        fragmentList.add(new ui6_4Fragment());

        ViewPager6FragmentAdapter adapter = new ViewPager6FragmentAdapter(this, fragmentList);
        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout() {

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setIcon(iconResId[position]);
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {
                toolbar.setTitle(toolbarTitle[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(@NonNull TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(@NonNull TabLayout.Tab tab) {

            }
        });
    }
}
