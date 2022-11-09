package com.daeseong.bottomnavigationview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main6Activity.class.getSimpleName();

    private ViewPager viewPager;
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

        tab_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPage(0);
            }
        });

        tab_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPage(1);
            }
        });

        tab_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPage(2);
            }
        });

        tab_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPage(3);
            }
        });

        tab_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPage(4);
            }
        });
    }

    private void init(){

        viewPager = findViewById(R.id.viewPager);

        tab_layout = findViewById(R.id.tab_layout);

        tab_item1 = (View)tab_layout.findViewById(R.id.tab_item1);
        iv_item1 = (ImageView)tab_item1.findViewById(R.id.iv_item);
        tv_item1 = (TextView)tab_item1.findViewById(R.id.tv_item);

        tab_item2 = (View)tab_layout.findViewById(R.id.tab_item2);
        iv_item2 = (ImageView)tab_item2.findViewById(R.id.iv_item);
        tv_item2 = (TextView)tab_item2.findViewById(R.id.tv_item);

        tab_item3 = (View)tab_layout.findViewById(R.id.tab_item3);
        iv_item3 = (ImageView)tab_item3.findViewById(R.id.iv_item);
        tv_item3 = (TextView)tab_item3.findViewById(R.id.tv_item);

        tab_item4 = (View)tab_layout.findViewById(R.id.tab_item4);
        iv_item4 = (ImageView)tab_item4.findViewById(R.id.iv_item);
        tv_item4 = (TextView)tab_item4.findViewById(R.id.tv_item);

        tab_item5 = (View)tab_layout.findViewById(R.id.tab_item5);
        iv_item5 = (ImageView)tab_item5.findViewById(R.id.iv_item);
        tv_item5 = (TextView)tab_item5.findViewById(R.id.tv_item);

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

    private void selectPage(int index){

        viewPager.setCurrentItem(index);

        if(index == 0){

            iv_item1.setImageResource(R.drawable.bookg);
            iv_item2.setImageResource(R.drawable.book);
            iv_item3.setImageResource(R.drawable.book);
            iv_item4.setImageResource(R.drawable.book);
            iv_item5.setImageResource(R.drawable.book);

        } else if(index == 1){

            iv_item1.setImageResource(R.drawable.book);
            iv_item2.setImageResource(R.drawable.bookg);
            iv_item3.setImageResource(R.drawable.book);
            iv_item4.setImageResource(R.drawable.book);
            iv_item5.setImageResource(R.drawable.book);

        } else if(index == 2){

            iv_item1.setImageResource(R.drawable.book);
            iv_item2.setImageResource(R.drawable.book);
            iv_item3.setImageResource(R.drawable.bookg);
            iv_item4.setImageResource(R.drawable.book);
            iv_item5.setImageResource(R.drawable.book);

        } else if(index == 3){

            iv_item1.setImageResource(R.drawable.book);
            iv_item2.setImageResource(R.drawable.book);
            iv_item3.setImageResource(R.drawable.book);
            iv_item4.setImageResource(R.drawable.bookg);
            iv_item5.setImageResource(R.drawable.book);

        } else if(index == 4){

            iv_item1.setImageResource(R.drawable.book);
            iv_item2.setImageResource(R.drawable.book);
            iv_item3.setImageResource(R.drawable.book);
            iv_item4.setImageResource(R.drawable.book);
            iv_item5.setImageResource(R.drawable.bookg);
        }
    }

    private void setupViewPager(ViewPager v) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(Fragment1.newInstance());// new Fragment1());
        viewPagerAdapter.addFragment(Fragment2.newInstance());// new Fragment2());
        viewPagerAdapter.addFragment(Fragment3.newInstance());// new Fragment3());
        viewPagerAdapter.addFragment(Fragment4.newInstance());// new Fragment4());
        viewPagerAdapter.addFragment(Fragment5.newInstance());// new Fragment5());
        v.setAdapter(viewPagerAdapter);
    }
}