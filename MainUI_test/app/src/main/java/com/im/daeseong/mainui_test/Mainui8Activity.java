package com.im.daeseong.mainui_test;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Mainui8Activity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui8);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        setupViewPage(viewPager);

        tabLayout = findViewById(R.id.tab);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(((Page8Adapter)viewPager.getAdapter()).getPageTitle(position));
        }).attach();
    }

    private void setupViewPage(ViewPager2 viewPager) {
        Page8Adapter adapter = new Page8Adapter(this);
        adapter.addFragment(new ui6_1Fragment(), "tab1");
        adapter.addFragment(new ui6_2Fragment(), "tab2");
        adapter.addFragment(new ui6_3Fragment(), "tab3");
        adapter.addFragment(new ui6_4Fragment(), "tab4");
        viewPager.setAdapter(adapter);
    }

    public void backPress(View view) {
        Toast.makeText(this, "backPress", Toast.LENGTH_SHORT).show();
    }

    public void profileClick(View view) {
        Toast.makeText(this, "profileClick", Toast.LENGTH_SHORT).show();
    }
}
