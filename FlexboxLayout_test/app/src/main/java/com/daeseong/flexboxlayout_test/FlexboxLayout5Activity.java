package com.daeseong.flexboxlayout_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.flexbox.FlexboxLayout;

public class FlexboxLayout5Activity extends AppCompatActivity {

    private static final String TAG = FlexboxLayout5Activity.class.getSimpleName();

    private FlexboxLayout fL1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox_layout5);

        init();

        initData();
    }

    private void init() {

        fL1  = (FlexboxLayout)findViewById(R.id.fL1);
    }

    private void initData() {

        fL1.removeAllViews();

        for (int i =0; i < 10; i++) {

            ConstraintLayout cl = (ConstraintLayout) LayoutInflater.from(FlexboxLayout5Activity.this).inflate(R.layout.flexbox_item, null);
            cl.setOnClickListener(flexClicked);
            cl.setTag(i);

            tv1 = (TextView) cl.findViewById(R.id.tv1);
            tv1.setTextColor(Color.parseColor("#8B8E97"));
            tv1.setTypeface(null, Typeface.NORMAL);

            if( i % 2 == 0 ) {
                tv1.setText(String.format("Index Index:%d", i));
            } else {
                tv1.setText(String.format("Index:%d", i));
            }

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int marginLeft = UnitUtils.dip2px(this, 6);
            int marginTop = UnitUtils.dip2px(this, 8);
            params.setMargins(marginLeft, marginTop, 0, 0);
            cl.setLayoutParams(params);
            fL1.addView(cl);

        }
    }

    View.OnClickListener flexClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}