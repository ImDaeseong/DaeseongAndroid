package com.im.daeseong.viewpager_test;

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

import java.util.ArrayList;

public class ViewPager2Activity extends AppCompatActivity {

    private static final String TAG = ViewPager2Activity.class.getSimpleName();

    private ViewPager viewPager2;
    private Adapter2 Adapter2;
    private ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        arrayList = new ArrayList();
        arrayList.add(R.drawable.number1);
        arrayList.add(R.drawable.number2);
        arrayList.add(R.drawable.number3);
        arrayList.add(R.drawable.number4);

        viewPager2 = (ViewPager)findViewById(R.id.viewPager2);
        Adapter2 = new Adapter2(getLayoutInflater(), arrayList);
        viewPager2.setAdapter(Adapter2);
    }

    public class  Adapter2 extends PagerAdapter{
        ArrayList arrayList;
        LayoutInflater inflater;

        public Adapter2(LayoutInflater inflater, ArrayList arrayList){
            this.inflater = inflater;
            this.arrayList = arrayList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.pager2_adapter, null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageview2);

            int nRes = (int)arrayList.get(position);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(nRes);

            final int nIndex = position;

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "position:" + String.valueOf(nIndex), Snackbar.LENGTH_LONG).show();
                }
            });

            container.addView(view);

            return  view;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View)object);
        }
    }
}
