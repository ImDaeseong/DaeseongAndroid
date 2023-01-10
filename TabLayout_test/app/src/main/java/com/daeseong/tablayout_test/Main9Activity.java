package com.daeseong.tablayout_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.tabs.TabLayout;

public class Main9Activity extends AppCompatActivity {

    private static final String TAG = Main9Activity.class.getSimpleName();

    private TabLayout tl1;
    private View v1, v2;
    int nPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);

        tl1 = (TabLayout) findViewById(R.id.tl1);
        tl1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                nPos = tab.getPosition();
                initTab(nPos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        initTab(nPos);
    }

    private void initTab(int nIndex){

        if(nIndex == 0) {

            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.GONE);

        } else if(nIndex == 1) {

            v1.setVisibility(View.GONE);
            v2.setVisibility(View.VISIBLE);
        }

        tl1.getTabAt(nIndex).select();
    }

}