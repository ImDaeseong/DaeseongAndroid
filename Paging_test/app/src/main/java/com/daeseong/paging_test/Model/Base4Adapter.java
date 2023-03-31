package com.daeseong.paging_test.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.daeseong.paging_test.API.SearchApi;
import com.daeseong.paging_test.R;
import java.util.ArrayList;
import java.util.List;

public class Base4Adapter extends BaseAdapter {

    private static final String TAG = Base4Adapter.class.getSimpleName();

    private List<SearchApi.itemData> list  = new ArrayList<>();

    private TextView tv1, tv2, tv3;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SearchApi.itemData getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        }

        tv1 = (TextView)view.findViewById(R.id.tv1);
        tv2 = (TextView)view.findViewById(R.id.tv2);
        tv3 = (TextView)view.findViewById(R.id.tv3);

        SearchApi.itemData item = (SearchApi.itemData) getItem(i);

        tv1.setText(item.ID);
        tv2.setText(item.NAME);
        tv3.setText(item.HTMLURL);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, item.ID);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, item.NAME);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, item.HTMLURL);
            }
        });

        return view;
    }

    public void addAll(List<SearchApi.itemData> list){

        if (list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void add(List<SearchApi.itemData> list){

        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }
}
