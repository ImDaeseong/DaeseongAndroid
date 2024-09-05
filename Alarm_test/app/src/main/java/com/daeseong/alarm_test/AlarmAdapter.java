package com.daeseong.alarm_test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class AlarmAdapter extends BaseAdapter {

    private static final String TAG = AlarmAdapter.class.getSimpleName();

    private ArrayList<Alarm> arrayList = new ArrayList<Alarm>();

    private TextView tv1, tv2, tv3;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Alarm getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.alarm_item, parent, false);
        }

        tv1 = (TextView)convertView.findViewById(R.id.tv1);
        tv2 = (TextView)convertView.findViewById(R.id.tv2);
        tv3 = (TextView)convertView.findViewById(R.id.tv3);

        Alarm alarm = getItem(position);

        //Log.e(TAG, "alarm : " + alarm);

        tv1.setText(String.valueOf(alarm.getID()));
        tv2.setText(String.valueOf(alarm.getHour()));
        tv3.setText(String.valueOf(alarm.getMinute()));

        return convertView;
    }

    public void addItem(int id, int nHour, int nMinute){

        Alarm alarm = new Alarm(id, nHour, nMinute);
        this.arrayList.add(alarm);
    }

    public void removeItem(int id) {

        for(int i=0; i < arrayList.size(); i++){

            if ( arrayList.get(i).getID() == id){
                arrayList.remove(i);
                break;
            }
        }
    }

    public void updateItem(int id, int nHour, int nMinute){
        for(int i=0; i < arrayList.size(); i++){

            if ( arrayList.get(i).getID() == id){
                Alarm alarm = new Alarm(id, nHour, nMinute);
                arrayList.set(i, alarm);
                break;
            }
        }
    }

}
