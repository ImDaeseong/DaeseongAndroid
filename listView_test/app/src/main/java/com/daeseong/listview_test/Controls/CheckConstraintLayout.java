package com.daeseong.listview_test.Controls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Checkable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CheckConstraintLayout extends ConstraintLayout implements Checkable {

    private boolean isChecked;

    public CheckConstraintLayout(@NonNull Context context) {
        super(context);

        isChecked = false;

        setBackgroundColor(Color.GRAY);
    }

    public CheckConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckConstraintLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setChecked(boolean b) {

        if(b) {
            setBackgroundColor(Color.WHITE);
        } else {
            setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(isChecked ? false : true);
    }
}
