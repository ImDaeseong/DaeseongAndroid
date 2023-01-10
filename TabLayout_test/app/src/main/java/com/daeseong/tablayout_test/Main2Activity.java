package com.daeseong.tablayout_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private TabLayout tabLayout;
    private View divider_line;

    private TabItem tab1, tab2, tab3, tab4, tab5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tab1 = (TabItem)findViewById(R.id.tab1);
        tab2 = (TabItem)findViewById(R.id.tab2);
        tab3 = (TabItem)findViewById(R.id.tab3);
        tab4 = (TabItem)findViewById(R.id.tab4);
        tab5 = (TabItem)findViewById(R.id.tab5);

        divider_line = (View) findViewById(R.id.divider_line);

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

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.holo_orange_dark));

            tabLayout.getTabAt(0).setIcon(R.drawable.booka);
            tabLayout.getTabAt(1).setIcon(R.drawable.booka);
            tabLayout.getTabAt(2).setIcon(R.drawable.booka);
            tabLayout.getTabAt(3).setIcon(R.drawable.booka);
            tabLayout.getTabAt(4).setIcon(R.drawable.booka);
        } else if(nIndex == 1) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.win8_red));

            tabLayout.getTabAt(0).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(1).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(2).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(3).setIcon(R.drawable.bookb);
            tabLayout.getTabAt(4).setIcon(R.drawable.bookb);
        }  else if(nIndex == 2) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.DarkGoldenrod));

            tabLayout.getTabAt(0).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(1).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(2).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(3).setIcon(R.drawable.bookc);
            tabLayout.getTabAt(4).setIcon(R.drawable.bookc);
        } else if(nIndex == 3) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.FireBrick));

            tabLayout.getTabAt(0).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(1).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(2).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(3).setIcon(R.drawable.bookd);
            tabLayout.getTabAt(4).setIcon(R.drawable.bookd);
        } else if(nIndex == 4) {

            divider_line.setBackgroundColor(ContextCompat.getColor(this, R.color.Magenta));

            tabLayout.getTabAt(0).setIcon(R.drawable.booke);
            tabLayout.getTabAt(1).setIcon(R.drawable.booke);
            tabLayout.getTabAt(2).setIcon(R.drawable.booke);
            tabLayout.getTabAt(3).setIcon(R.drawable.booke);
            tabLayout.getTabAt(4).setIcon(R.drawable.booke);
        }
    }
}
