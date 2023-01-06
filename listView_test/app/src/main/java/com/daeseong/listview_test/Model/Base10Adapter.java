package com.daeseong.listview_test.Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.daeseong.listview_test.R;
import com.daeseong.listview_test.itemData;
import java.util.ArrayList;

public class Base10Adapter extends BaseAdapter {

    private static final String TAG = Base10Adapter.class.getSimpleName();

    private Activity activity;
    private ArrayList<itemData> list;

    public Base10Adapter(Activity activity, ArrayList<itemData> list){
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        int nCount = 0;
        if(list != null){
            nCount = list.size();
        }
        return nCount;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.list_item10, null);

        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        tv1.setText(list.get(i).getLocName());

        return view;
    }

    public void addAll(ArrayList<itemData> list){

        this.list = list;
        notifyDataSetChanged();
    }
}