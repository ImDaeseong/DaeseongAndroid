package com.im.daeseong.toast_test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ClickToast extends Toast implements View.OnClickListener {

    private LayoutInflater layoutInflater;
    private View view;
    private TextView tv1;

    public ClickToast(Context context, String sMsg){
        super(context);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.toast_layout1, null);
        tv1 = (TextView)view.findViewById(R.id.tvtoast);
        tv1.setText(sMsg);
        setView(view);
        setDuration(LENGTH_SHORT);
        show();
    }

    @Override
    public void onClick(View v) {
        try {
            Log.i("A", "onClick");
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
