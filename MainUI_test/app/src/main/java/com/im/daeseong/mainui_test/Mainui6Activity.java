package com.im.daeseong.mainui_test;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Mainui6Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    private int[] IconResID = {R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4};
    private int[] TollBarTitle = {R.string.Tab1,R.string.Tab2,R.string.Tab3,R.string.Tab4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui6);

        viewPager = (ViewPager) findViewById(R.id.myViewPager);
        tabLayout = (TabLayout) findViewById(R.id.TabLayout);
        toolbar = (Toolbar) findViewById(R.id.ToolBar);
        toolbar.setTitle(TollBarTitle[0]);
        toolbar.setTitleTextColor(Color.YELLOW);
        setSupportActionBar(toolbar);

        setViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutIcon();
    }

    private void setViewPager(){
        ui6_1Fragment myFragment1 = new ui6_1Fragment();
        ui6_2Fragment myFragment2 = new ui6_2Fragment();
        ui6_3Fragment myFragment3 = new ui6_3Fragment();
        ui6_4Fragment myFragment4 = new ui6_4Fragment();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(myFragment1);
        fragmentList.add(myFragment2);
        fragmentList.add(myFragment3);
        fragmentList.add(myFragment4);
        ViewPager6FragmentAdapter myFragmentAdapter = new ViewPager6FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myFragmentAdapter);
    }

    public void setTabLayoutIcon(){
        for(int i =0; i < 4;i++){
            tabLayout.getTabAt(i).setIcon(IconResID[i]);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.getMenu().clear();
                switch(tab.getPosition()){
                    case 0:
                        //toolbar.inflateMenu(R.menu.menu_one);
                        toolbar.setTitle(TollBarTitle[0]);
                        break;
                    case 1:
                        //toolbar.inflateMenu(R.menu.menu_two);
                        toolbar.setTitle(TollBarTitle[1]);
                        break;
                    case 2:
                        //toolbar.inflateMenu(R.menu.menu_three);
                        toolbar.setTitle(TollBarTitle[2]);
                        break;
                    case 3:
                        //toolbar.inflateMenu(R.menu.menu_three);
                        toolbar.setTitle(TollBarTitle[3]);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

}
