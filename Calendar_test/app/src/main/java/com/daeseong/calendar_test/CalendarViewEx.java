package com.daeseong.calendar_test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.daeseong.calendar_test.Adapter.Calendar1Adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarViewEx extends ConstraintLayout {

    private static final String TAG = CalendarViewEx.class.getSimpleName();


    private Context context;

    private TextView tv_title;
    private View clleft_arrow, clright_arrow;
    private GridView gridview1;

    private final Calendar calendar = Calendar.getInstance();

    private ArrayList<Date> arrayList = new ArrayList<>();

    public CalendarViewEx(Context context){
        super(context);
        init(context);
    }

    public CalendarViewEx(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarViewEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_view1, this);

        tv_title = view.findViewById(R.id.tv_title);
        clleft_arrow = view.findViewById(R.id.clleft_arrow);
        clright_arrow = view.findViewById(R.id.clright_arrow);
        gridview1 = view.findViewById(R.id.gridview1);

        clleft_arrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.add(Calendar.MONTH, -1);
                showCalendarView();
            }
        });

        clright_arrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.add(Calendar.MONTH, 1);
                showCalendarView();
            }
        });

        showCalendarView();
    }

    private void showCalendarView(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년MM월");

        //제목
        tv_title.setText(simpleDateFormat.format(calendar.getTime()));

        //Calendar.MONTH 값은 0 ~ 11  즉 현재 월을 구하기 위해서는 +1 읗 해야 한다
        //Calendar.DAY_OF_WEEK 값은 1 ~ 7 일 ~ 토

        Calendar tempcalendar = (Calendar) calendar.clone();
        tempcalendar.set(Calendar.DAY_OF_MONTH, 1);
        int nNonth = tempcalendar.get(Calendar.MONTH);

        int nDay = tempcalendar.get(Calendar.DAY_OF_WEEK) - 1;
        tempcalendar.add(Calendar.DAY_OF_MONTH, -nDay);

        //요일 개수 (7) * 달력칸(6) = 42
        arrayList.clear();
        for(int i=0; i< 42; i++){
            arrayList.add(tempcalendar.getTime());
            tempcalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        gridview1.setAdapter(new Calendar1Adapter(getContext(), arrayList, nNonth, nDay));

        gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Date date = arrayList.get(position);
                int nDay = date.getDate();
                String sDay = String.format("%02d",nDay);
                Log.e(TAG, "sDay:" + sDay);

            }

        });


    }




}
