package com.daeseong.tablayout_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class Main7Activity extends AppCompatActivity {

    private static final String TAG = Main7Activity.class.getSimpleName();

    private TabLayout tabLayout;
    private View divider_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        divider_line = (View) findViewById(R.id.divider_line);

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

        initTab(0);
    }

    private void initTab(int nIndex){

        if(nIndex == 0) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.holo_orange_dark));
        } else if(nIndex == 1) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.win8_red));
        }  else if(nIndex == 2) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.DarkGoldenrod));
        } else if(nIndex == 3) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.FireBrick));
        } else if(nIndex == 4) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.Magenta));
        }

        tabLayout.getTabAt(nIndex).select();
    }
}
