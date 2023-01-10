package com.daeseong.tablayout_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.material.tabs.TabLayout;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.e(TAG, "tab.getPosition():" +  tab.getPosition());
                initTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initTab(int nIndex){

        if(nIndex == 0) {

            tabLayout.getTabAt(0).setIcon(R.drawable.booka);
            tabLayout.getTabAt(1).setIcon(R.drawable.booka);
            tabLayout.getTabAt(2).setIcon(R.drawable.booka);
            tabLayout.getTabAt(3).setIcon(R.drawable.booka);
            tabLayout.getTabAt(4).setIcon(R.drawable.booka);
        } else if(nIndex == 1) {

            tabLayout.getTabAt(0).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(1).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(2).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(3).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(4).setIcon(R.drawable.bookb);
        }  else if(nIndex == 2) {

            tabLayout.getTabAt(0).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(1).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(2).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(3).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(4).setIcon(R.drawable.bookc);
        } else if(nIndex == 3) {

            tabLayout.getTabAt(0).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(1).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(2).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(3).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(4).setIcon(R.drawable.bookd);
        } else if(nIndex == 4) {

            tabLayout.getTabAt(0).setIcon(R.drawable.booke);
            tabLayout.getTabAt(1).setIcon(R.drawable.booke);
            tabLayout.getTabAt(2).setIcon(R.drawable.booke);
            tabLayout.getTabAt(3).setIcon(R.drawable.booke);
            tabLayout.getTabAt(4).setIcon(R.drawable.booke);
        }
    }
}
