package com.daeseong.calendar_test.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.daeseong.calendar_test.R;

public class Calendar1Adapter extends BaseAdapter {

    private static final String TAG = Calendar1Adapter.class.getSimpleName();

    private Context context;

    TextView tv_day1, tv_day2;

    private ArrayList<Date> arrayList = new ArrayList<>();

    public Calendar1Adapter(Context context, ArrayList<Date> arrayList){

        this.context = context;
        this.arrayList = arrayList;
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

        //날짜
        final Date date = arrayList.get(position);
        int nDay = date.getDate();
        String sDay = String.format("%02d",nDay);


        //일요일 날짜 색상 빨간색
        Calendar calendar = getCalendar(date);
        int nWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(nWeek == 1){
            tv_day1.setTextColor(Color.RED);
        }else {
            tv_day1.setTextColor(Color.parseColor("#000000"));
        }


        tv_day1.setText(sDay);

        return convertView;
    }

    private Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Date getCalendarDate(Calendar calendar) {
        return calendar.getTime();
    }

}
