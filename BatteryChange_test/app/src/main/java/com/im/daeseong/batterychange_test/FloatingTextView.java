package com.im.daeseong.batterychange_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FloatingTextView  {

    private View floatingview;
    private TextView tvtoast;

    public FloatingTextView(ViewGroup viewGroup){

        floatingview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_floating_text_view, viewGroup, false);

        tvtoast = (TextView)floatingview.findViewById(R.id.tvtoast);
    }

    public View getFloatingview(){
        return floatingview;
    }

    public void setText(String sText){
        tvtoast.setText(sText);
    }
}
