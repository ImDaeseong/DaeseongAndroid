package com.im.daeseong.viewpager_test;

import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class ViewPager5Activity extends AppCompatActivity {

    private static final String TAG = ViewPager5Activity.class.getSimpleName();

    private android.support.v7.widget.Toolbar toolbar;
    private Pagar5Adapter pagar5Adapter;
    private ViewPager viewPager5;

    Handler handler;
    Runnable runnable;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager5);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagar5Adapter = new Pagar5Adapter(this);

        viewPager5 =(ViewPager)findViewById(R.id.viewPager5);
        viewPager5.setAdapter(pagar5Adapter);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                int i = viewPager5.getCurrentItem();
                i++;

                Log.e(TAG, "i:" + String.valueOf(i));

                viewPager5.setCurrentItem(i, true);

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 2000, 2000);

    }


    public class Pagar5Adapter extends PagerAdapter{
        private Context context;
        LayoutInflater inflater;

        public int [] images = {R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4};

        public Pagar5Adapter(Context context){
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.pager5_adapter, container, false);
            ImageView imageview5 = (ImageView)view.findViewById(R.id.imageview5);
            imageview5.setImageResource(images[position]);

            final int nIndex = position;

            imageview5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Image" + (nIndex + 1), Snackbar.LENGTH_LONG).show();
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == (ConstraintLayout)object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ConstraintLayout)object);
        }
    }
}
