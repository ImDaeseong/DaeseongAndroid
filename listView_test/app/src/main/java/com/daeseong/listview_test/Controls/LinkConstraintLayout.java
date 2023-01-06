package com.daeseong.listview_test.Controls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.daeseong.listview_test.R;

public class LinkConstraintLayout extends ConstraintLayout implements Checkable {

    private boolean isChecked;

    public LinkConstraintLayout(@NonNull Context context) {
        super(context);

        isChecked = false;
    }

    public LinkConstraintLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public LinkConstraintLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setChecked(boolean b) {
        if(isChecked != b) {
            isChecked = b;
            refreshDrawableState();
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

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();

        for(int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if(view.getId() == R.id.tv1) {
                if(isChecked) {
                    ((TextView)view).setTextColor(Color.RED);
                }
                else {
                    ((TextView)view).setTextColor(Color.BLACK);
                }
            }
        }
    }
}
