package com.daeseong.bottomnavigationview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private View tab_layout;
    private View tab_item1, tab_item2, tab_item3, tab_item4, tab_item5;

    private ImageView iv_item1, iv_item2, iv_item3, iv_item4, iv_item5;
    private TextView tv_item1, tv_item2, tv_item3, tv_item4, tv_item5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        init();
        setupViewPager(viewPager);
        selectPage(0);

        tab_item1.setOnClickListener(view -> selectPage(0));
        tab_item2.setOnClickListener(view -> selectPage(1));
        tab_item3.setOnClickListener(view -> selectPage(2));
        tab_item4.setOnClickListener(view -> selectPage(3));
        tab_item5.setOnClickListener(view -> selectPage(4));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectPage(position);
            }
        });
    }

    private void init() {

        viewPager = findViewById(R.id.viewPager);
        tab_layout = findViewById(R.id.tab_layout);

        tab_item1 = tab_layout.findViewById(R.id.tab_item1);
        iv_item1 = tab_item1.findViewById(R.id.iv_item);
        tv_item1 = tab_item1.findViewById(R.id.tv_item);

        tab_item2 = tab_layout.findViewById(R.id.tab_item2);
        iv_item2 = tab_item2.findViewById(R.id.iv_item);
        tv_item2 = tab_item2.findViewById(R.id.tv_item);

        tab_item3 = tab_layout.findViewById(R.id.tab_item3);
        iv_item3 = tab_item3.findViewById(R.id.iv_item);
        tv_item3 = tab_item3.findViewById(R.id.tv_item);

        tab_item4 = tab_layout.findViewById(R.id.tab_item4);
        iv_item4 = tab_item4.findViewById(R.id.iv_item);
        tv_item4 = tab_item4.findViewById(R.id.tv_item);

        tab_item5 = tab_layout.findViewById(R.id.tab_item5);
        iv_item5 = tab_item5.findViewById(R.id.iv_item);
        tv_item5 = tab_item5.findViewById(R.id.tv_item);

        iv_item1.setImageResource(R.drawable.book);
        tv_item1.setText("메뉴1");

        iv_item2.setImageResource(R.drawable.book);
        tv_item2.setText("메뉴2");

        iv_item3.setImageResource(R.drawable.book);
        tv_item3.setText("메뉴3");

        iv_item4.setImageResource(R.drawable.book);
        tv_item4.setText("메뉴4");

        iv_item5.setImageResource(R.drawable.book);
        tv_item5.setText("메뉴5");
    }

    private void selectPage(int index) {

        viewPager.setCurrentItem(index, false);

        iv_item1.setImageResource(R.drawable.book);
        iv_item2.setImageResource(R.drawable.book);
        iv_item3.setImageResource(R.drawable.book);
        iv_item4.setImageResource(R.drawable.book);
        iv_item5.setImageResource(R.drawable.book);

        if (index == 0) {
            iv_item1.setImageResource(R.drawable.bookg);
        } else if (index == 1) {
            iv_item2.setImageResource(R.drawable.bookg);
        } else if (index == 2) {
            iv_item3.setImageResource(R.drawable.bookg);
        } else if (index == 3) {
            iv_item4.setImageResource(R.drawable.bookg);
        } else if (index == 4) {
            iv_item5.setImageResource(R.drawable.bookg);
        }
    }

    private void setupViewPager(ViewPager2 viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(Fragment1.newInstance());
        viewPagerAdapter.addFragment(Fragment2.newInstance());
        viewPagerAdapter.addFragment(Fragment3.newInstance());
        viewPagerAdapter.addFragment(Fragment4.newInstance());
        viewPagerAdapter.addFragment(Fragment5.newInstance());
        viewPager.setAdapter(viewPagerAdapter);
    }
}
