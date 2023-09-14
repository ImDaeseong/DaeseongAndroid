package com.im.daeseong.viewpager_test;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import com.google.android.material.snackbar.Snackbar;//import android.support.design.widget.Snackbar;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;//import android.support.v4.view.PagerAdapter;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class ViewPager1Activity extends AppCompatActivity {

    private static final String TAG = ViewPager1Activity.class.getSimpleName();

    private ViewPager viewPager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager1);

        viewPager1 = (ViewPager)findViewById(R.id.viewPager1);

        Adapter1 Adapter1 = new Adapter1(this);
        viewPager1.setAdapter(Adapter1);
    }

    class Adapter1 extends PagerAdapter {

        Context context;
        LayoutInflater layoutInflater;

        int[]Images = {
                R.drawable.number1,
                R.drawable.number2,
                R.drawable.number3,
                R.drawable.number4
        };

        int [] lstColors = {
                Color.rgb(55,55,55),
                Color.rgb(239,85,85),
                Color.rgb(110,49,89),
                Color.rgb(1,188,212)
        };

        Adapter1(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return Images.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.pager1_adapter, container, false);
            ImageView imageView1 = (ImageView)view.findViewById(R.id.imageview1);
            imageView1.setImageResource(Images[position]);

            final int nIndex = position;

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "position:" + String.valueOf(nIndex), Snackbar.LENGTH_LONG).show();
                }
            });


            LinearLayout linearLayout = view.findViewById(R.id.imgLinearLayout);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                linearLayout.setBackgroundColor(lstColors[position]);
            }

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == (LinearLayout)object);
        }
    }

}
