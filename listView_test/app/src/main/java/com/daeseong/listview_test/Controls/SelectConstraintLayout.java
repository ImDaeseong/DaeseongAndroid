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
import java.util.HashMap;

public class SelectConstraintLayout extends ConstraintLayout implements Checkable {

    private static final String TAG = SelectConstraintLayout.class.getSimpleName();

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    private boolean isChecked;

    public SelectConstraintLayout(@NonNull Context context) {
        super(context);

        isChecked = false;
    }

    public SelectConstraintLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public SelectConstraintLayout(Context context, AttributeSet attrs, int defStyle) {
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

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.getId() == R.id.tv1) {
                if (isChecked) {
                    ((TextView)view).setTextColor(Color.RED);
                } else {
                    ((TextView)view).setTextColor(Color.BLACK);
                }
            }
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);

        if (isChecked())
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);

        return drawableState;
    }
}
