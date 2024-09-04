package com.im.daeseong.mainui_test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Objects;

public class Mainui10Activity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager2 viewPager;
    private Toolbar toolbar;
    private View mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui10);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar10_main);

        tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.container);

        setupViewPage(viewPager);

        new TabLayoutMediator(tabs, viewPager, (tab, position) ->
                tab.setText(((Page8Adapter) Objects.requireNonNull(viewPager.getAdapter())).getPageTitle(position))
        ).attach();
    }

    private void setupViewPage(ViewPager2 viewPager){
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
