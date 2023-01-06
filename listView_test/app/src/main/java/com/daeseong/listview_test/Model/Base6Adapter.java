package com.daeseong.listview_test.Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.daeseong.listview_test.R;
import java.util.List;

public class Base6Adapter extends BaseAdapter {

    private static final String TAG = Base6Adapter.class.getSimpleName();

    private Activity activity;
    private List<String> list;

    public Base6Adapter(Activity activity, List<String> list){
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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.grid_item1, null);

        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        tv1.setText(list.get(i));

        return view;
    }

}
