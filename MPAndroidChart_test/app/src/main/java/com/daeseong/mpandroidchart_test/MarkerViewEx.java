package com.daeseong.mpandroidchart_test;

import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class MarkerViewEx extends MarkerView {

    private TextView tv1;

    public MarkerViewEx(Context context, int layoutResource) {
        super(context, layoutResource);
        tv1 = findViewById(R.id.tv1);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tv1.setText("값: " + e.getY());
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}