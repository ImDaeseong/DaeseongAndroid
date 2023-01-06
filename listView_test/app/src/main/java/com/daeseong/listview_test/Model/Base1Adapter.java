package com.daeseong.listview_test.Model;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.daeseong.listview_test.R;
import com.daeseong.listview_test.itemData;
import java.util.List;

public class Base1Adapter extends BaseAdapter {

    private static final String TAG = Base1Adapter.class.getSimpleName();

    private Activity activity;
    private List<itemData> list;

    private int nSelect = -1;

    public Base1Adapter(Activity activity, List<itemData> list){
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
        view = inflater.inflate(R.layout.list_item1, null);

        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        tv1.setText(list.get(i).getLocName());

        highlightItem(i, view);

        //setBackgroundColor(i,view);

        //setViewClick(i,view);

        return view;
    }

    public void addAll(List<itemData> list){

        this.list = list;
        notifyDataSetChanged();
    }

    public void add(List<itemData> list){

        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(itemData item){
        this.list.add(item);
    }

    public void removeItem(int i){
        this.list.remove(i);
    }

    public void removeAllItem(){
        this.list.clear();
    }

    public void setChangeBackColor(View view, int nSelect) {

        ViewGroup parent = ((ViewGroup) view.getParent());

        for (int i = 0; i < parent.getChildCount() ; i++) {

            View curView = parent.getChildAt(i);
            if(nSelect == i){
                curView.setBackgroundColor(Color.GRAY);
            }else {
                curView.setBackgroundColor(Color.WHITE);
            }
        }
    }

    private void highlightItem(int i, View view){

        if(nSelect == i){
            view.setBackgroundColor(Color.GRAY);
        }else {
            view.setBackgroundColor(Color.WHITE);
        }
    }

    private void setBackgroundColor(int i, View view){

        if (i % 2 == 0) {
            view.setBackgroundColor(Color.GRAY);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }
    }

    private void setViewClick(int i, View view){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setChangeBackColor(view, i);
            }
        });
    }

    public void setSelectItem(int nSelect){
        this.nSelect = nSelect;
    }
}
