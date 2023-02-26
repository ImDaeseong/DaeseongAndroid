package com.daeseong.listview_test.Model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.daeseong.listview_test.R;
import com.daeseong.listview_test.itemData;
import java.util.List;

public class Base11Adapter extends ArrayAdapter<itemData> {

    private static final String TAG = Base11Adapter.class.getSimpleName();

    private final ListView lv1;
    private Context context;
    private List<itemData> list;

    private boolean[] selectItmes;

    public Base11Adapter(Context context, List<itemData> list, ListView lv1) {
        super(context , R.layout.list_item11, list);
        this.context = context;
        this.list = list;
        this.lv1 = lv1;
        selectItmes = new boolean[list.size()];
    }

    public boolean[] getSelectedItemList() {
        return selectItmes;
    }

    public void selectItem(int nPos) {
        selectItmes[nPos] = !selectItmes[nPos];
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public itemData getItem(int i) {
        return list.get(i);
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item11,null);
        }

        TextView tv1 = view.findViewById(R.id.tv1);
        ImageView iv1 = view.findViewById(R.id.iv1);

        if ( lv1.isItemChecked(i) ) {

            tv1.setText(list.get(i).getLocName());
            tv1.setTextColor(Color.parseColor("#333333"));
            tv1.setTypeface(null, Typeface.BOLD);
            view.setBackgroundColor(Color.LTGRAY);
        } else {

            tv1.setText(list.get(i).getLocName());
            tv1.setTextColor(Color.parseColor("#333333"));
            tv1.setTypeface(null, Typeface.NORMAL);
            view.setBackgroundColor(Color.WHITE);
        }

        //view.setBackgroundColor(selectItmes[i] ? Color.LTGRAY : Color.TRANSPARENT);

        return view;
    }

}
