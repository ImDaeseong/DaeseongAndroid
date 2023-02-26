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
import java.util.ArrayList;

public class Base2Adapter extends BaseAdapter {

    private static final String TAG = Base2Adapter.class.getSimpleName();

    private Activity activity;
    private ArrayList<itemData> list;

    private int nSelect = -1;

    public ViewItemClickListener listener;

    public void setViewItemClickListener(ViewItemClickListener listener){
        this.listener = listener;
    }

    public interface ViewItemClickListener {
        void onViewItemClick(int nIndex);
    }

    public Base2Adapter(Activity activity, ArrayList<itemData> list){
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
        view = inflater.inflate(R.layout.list_item2, null);

        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        tv1.setText(list.get(i).getLocName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onViewItemClick(i);
                }
            }
        });

        highlightItem(i, view);

        return view;
    }

    public void addAll(ArrayList<itemData> list){

        this.list = list;
        notifyDataSetChanged();
    }

    public void add(ArrayList<itemData> list){

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

    private void highlightItem(int i, View view){

        if(nSelect == i){
            view.setBackgroundColor(Color.GRAY);
        }else {
            view.setBackgroundColor(Color.WHITE);
        }
    }

    public void setSelectItem(int nSelect){
        this.nSelect = nSelect;
    }
}
