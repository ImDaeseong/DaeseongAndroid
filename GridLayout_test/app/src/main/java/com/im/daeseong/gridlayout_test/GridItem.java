package com.im.daeseong.gridlayout_test;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;//import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

public class GridItem extends ConstraintLayout {

    private TextView tv1;
    private String sText;

    public GridItem(Context context){
        super(context);
        init(context);
    }

    public GridItem(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }

    public GridItem(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.grid_item, this, true);
        tv1 = (TextView)findViewById(R.id.tv1);
    }

    public void setText(String sText){
        this.sText = sText;
        tv1.setText(sText);
    }

    public String getText() {
        return sText;
    }
}
