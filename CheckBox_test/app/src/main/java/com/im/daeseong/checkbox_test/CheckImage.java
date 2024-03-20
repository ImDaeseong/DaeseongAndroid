package com.im.daeseong.checkbox_test;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class CheckImage extends AppCompatImageView {

    private boolean bCheck = false;
    private int selectImgID = R.drawable.check_selected;
    private int unselectImgID = R.drawable.check_unselected;

    public CheckImage(Context context) {
        super(context);
        init();
    }

    public CheckImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        setOnClickListener(v -> {
            toggleChecked();
        });
    }

    private void toggleChecked() {
        this.bCheck = !bCheck;
        setImageResource(bCheck ? selectImgID : unselectImgID);
    }

    public void setCheck(boolean bCheck) {
        this.bCheck = bCheck;
        setImageResource(bCheck ? selectImgID : unselectImgID);
    }

    public boolean isChecked() {
        return this.bCheck;
    }
}