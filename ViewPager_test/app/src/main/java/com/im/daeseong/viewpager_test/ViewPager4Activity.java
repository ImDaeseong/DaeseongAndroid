package com.im.daeseong.viewpager_test;

import android.content.Context;
import android.graphics.Color;
import com.google.android.material.snackbar.Snackbar;//import android.support.design.widget.Snackbar;
import androidx.viewpager.widget.PagerAdapter;//import android.support.v4.view.PagerAdapter;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;//import android.support.v4.view.ViewPager;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewPager4Activity extends AppCompatActivity {

    private static final String TAG = ViewPager4Activity.class.getSimpleName();

    private Pagar4Adapter pagar4Adapter;
    private ViewPager viewPager4;
    private PagerTitleStrip pagerTitleStrip;
    private List<DataItem> list = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager4);

        viewPager4 = (ViewPager)findViewById(R.id.view_pager4);
        pagerTitleStrip = (PagerTitleStrip)findViewById(R.id.title_strip);

        list = new LoadData(this).getAll();

        pagar4Adapter = new Pagar4Adapter(this, list);
        viewPager4.setAdapter(pagar4Adapter);
        viewPager4.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            int selectColor = list.get(position).getBgColor();
            Log.e(TAG, "onPageSelected:" + String.valueOf(selectColor));
            pagerTitleStrip.setTextColor(selectColor);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    public class LoadData{
        String[] titles;
        String[] colors;
        int [] images = {R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4};

        public LoadData(Context context){
            titles = context.getResources().getStringArray(R.array.titles);
            colors = context.getResources().getStringArray(R.array.colors);
        }

        public List<DataItem> getAll(){
            List<DataItem> list = new ArrayList<>();
            for(int i=0; i< images.length; i++){
                String title = titles[i];
                int imgID = images[i];
                int color = Color.parseColor(colors[i]);
                DataItem item = new DataItem(title, imgID, color);
                list.add(item);
            }
            return list;
        }
    }

    public class Pagar4Adapter extends PagerAdapter {

        Context context;
        LayoutInflater inflater;
        List<DataItem> list;

        public Pagar4Adapter(Context context, List<DataItem> list){
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.pager4_adapter, container, false);
            DataItem dataItem = list.get(position);

            LinearLayout bgColor = (LinearLayout)view.findViewById(R.id.bgColor);
            ImageView imageView4 = (ImageView) view.findViewById(R.id.imageview4);
            TextView title = (TextView)view.findViewById(R.id.title);

            final int nIndex = position;
            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "position:" + String.valueOf(nIndex), Snackbar.LENGTH_LONG).show();
                }
            });


            bgColor.setBackgroundColor(dataItem.getBgColor());
            imageView4.setImageResource(dataItem.getImgID());
            title.setText(dataItem.getTitle());

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == (LinearLayout)object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }

    public class DataItem{
        String title;
        int imgID;
        int bgColor;

        public DataItem(String title, int imgID, int bgColor){
            this.title = title;
            this.imgID = imgID;
            this.bgColor = bgColor;
        }

        public String getTitle(){
            return title;
        }
        public int getImgID(){
            return imgID;
        }
        public int getBgColor(){
            return bgColor;
        }
    }

}
