package com.daeseong.calendar_test.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import com.daeseong.calendar_test.R;

public class Calendar1Adapter extends BaseAdapter {

    private static final String TAG = Calendar1Adapter.class.getSimpleName();

    private Context context;

    TextView tv_day1, tv_day2;

    private ArrayList<Date> arrayList = new ArrayList<>();
    private int nMonth, nDay;

    public Calendar1Adapter(Context context, ArrayList<Date> arrayList, int nMonth, int nDay){

        this.context = context;
        this.arrayList = arrayList;
        this.nMonth = nMonth;
        this.nDay = nDay;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.calendar_item1, parent, false);
        }

        tv_day1 = (TextView)convertView.findViewById(R.id.tv_day1);
        tv_day2 = (TextView)convertView.findViewById(R.id.tv_day2);

        final Date date = arrayList.get(position);
        int nDay = date.getDate();
        String sDay = String.format("%02d",nDay);

        tv_day1.setText(sDay);
        tv_day2.setText("");

        return convertView;
    }

}
