package com.daeseong.flexboxlayout_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.flexbox.FlexboxLayout;
import java.util.HashMap;

public class FlexboxLayout3Activity extends AppCompatActivity {

    private static final String TAG = FlexboxLayout3Activity.class.getSimpleName();

    private Button btn1;

    private FlexboxLayout fL1;
    private ConstraintLayout cLBG;
    private TextView tv1;

    private HashMap<Integer, Boolean> checkMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox_layout3);

        init();

        //initTest();

        initData();
    }

    private void init() {

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i < checkMap.size(); i++) {

                    Log.e(TAG, "select:" + i + checkMap.get(i).toString() );
                }

            }
        });

        fL1 = (FlexboxLayout)findViewById(R.id.fL1);
    }

    private void initTest() {

        LayoutInflater inflater = LayoutInflater.from(this);
        cLBG = (ConstraintLayout) inflater.inflate(R.layout.flexbox_item, null);
        cLBG.setBackgroundResource(R.drawable.border_normal);
        cLBG.setOnClickListener(flexClicked);

        tv1 = (TextView) cLBG.findViewById(R.id.tv1);
        tv1.setTextColor(Color.parseColor("#8B8E97"));
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setText("testtesst");

        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int marginLeft = UnitUtils.dip2px(this, 6);
        int marginTop = UnitUtils.dip2px(this, 8);
        params.setMargins(marginLeft, marginTop, 0, 0);
        cLBG.setLayoutParams(params);
        fL1.addView(cLBG);
    }

    private void initData() {

        fL1.removeAllViews();
        checkMap.clear();

        for (int i =0; i < 10; i++) {

            cLBG = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.flexbox_item, null);
            cLBG.setBackgroundResource(R.drawable.border_normal);
            cLBG.setOnClickListener(flexClicked);
            cLBG.setTag(i);

            tv1 = (TextView) cLBG.findViewById(R.id.tv1);
            tv1.setTextColor(Color.parseColor("#8B8E97"));
            tv1.setTypeface(null, Typeface.NORMAL);
            tv1.setText(String.format("Index:%d", i));

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int marginLeft = UnitUtils.dip2px(this, 6);
            int marginTop = UnitUtils.dip2px(this, 8);
            params.setMargins(marginLeft, marginTop, 0, 0);
            cLBG.setLayoutParams(params);
            fL1.addView(cLBG);

            checkMap.put(i, false);
        }
    }

    View.OnClickListener flexClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {

                for (int i = 0; i < fL1.getChildCount(); i++) {

                    ConstraintLayout cL = (ConstraintLayout) fL1.getChildAt(i);
                    if (v.getTag() == cL.getTag()) {

                        int nTextColor;
                        int nTextStyle;
                        if ( checkMap.get(i) == true ) {

                            checkMap.put(i, false);
                            cL.setBackgroundResource(R.drawable.border_normal);
                            nTextColor = Color.parseColor("#8B8E97");
                            nTextStyle = Typeface.NORMAL;

                        } else {

                            checkMap.put(i, true);
                            cL.setBackgroundResource(R.drawable.border_select);
                            nTextColor = Color.parseColor("#333333");
                            nTextStyle = Typeface.BOLD;
                        }

                        for (int j = 0; j < cL.getChildCount(); j++) {

                            View v_textview = cL.getChildAt(j);
                            if (v_textview instanceof TextView) {

                                TextView tv = (TextView) v_textview;
                                tv.setTextColor(nTextColor);
                                tv.setTypeface(null, nTextStyle);
                            }
                        }
                    }
                }

            } catch (Exception ex) {
            }
        }
    };


}