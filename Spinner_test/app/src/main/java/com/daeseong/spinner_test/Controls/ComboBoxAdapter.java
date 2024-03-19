package com.daeseong.spinner_test.Controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.daeseong.spinner_test.R;
import java.util.ArrayList;
import java.util.List;

public class ComboBoxAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public ComboBoxAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.combobox_item, null);
        }

        TextView textView = view.findViewById(R.id.tv1);
        textView.setText(list.get(position));

        return view;
    }

    public void addItem(String item){
        list.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(String item){
        list.remove(item);
        notifyDataSetChanged();
    }

    public List<String> getList() {
        return list;
    }
}