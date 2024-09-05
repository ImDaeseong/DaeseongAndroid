package com.daeseong.flexboxlayout_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.flexbox.FlexboxLayout;

public class FlexboxLayout4Activity extends AppCompatActivity {

    private static final String TAG = FlexboxLayout4Activity.class.getSimpleName();

    private View cl1, cl2, cl3, cl4, cl5;
    private TextView t1, t2, t3, t4, t5;

    private View cl11, cl22, cl33, cl44, cl55;
    private TextView t11, t22, t33, t44, t55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox_layout4);

        init();

        initData();
    }

    private void init() {

        cl1 = findViewById(R.id.cl1);
        cl2 = findViewById(R.id.cl2);
        cl3 = findViewById(R.id.cl3);
        cl4 = findViewById(R.id.cl4);
        cl5 = findViewById(R.id.cl5);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);

        cl11 = findViewById(R.id.cl11);
        cl22 = findViewById(R.id.cl22);
        cl33 = findViewById(R.id.cl33);
        cl44 = findViewById(R.id.cl44);
        cl55 = findViewById(R.id.cl55);

        t11 = findViewById(R.id.t11);
        t22 = findViewById(R.id.t22);
        t33 = findViewById(R.id.t33);
        t44 = findViewById(R.id.t44);
        t55 = findViewById(R.id.t55);
    }

    private void initData() {

        t1.setText("테스트 데이터1");
        t2.setText("데이터2");
        t3.setText("test 데이터3");
        t4.setText("테스트 데이터4");
        t5.setText("데이터5");

        t11.setText("테스트 데이터1");
        t22.setText("데이터2");
        t33.setText("test 데이터3");
        t44.setText("테스트 데이터4");
        t55.setText("데이터5");

        int nMarginRight = UnitUtils.dip2px(FlexboxLayout4Activity.this, 4);

        FlexboxLayout.LayoutParams layoutParams1 = (FlexboxLayout.LayoutParams) cl1.getLayoutParams();
        layoutParams1.rightMargin = nMarginRight;
        cl1.setLayoutParams(layoutParams1);

        FlexboxLayout.LayoutParams layoutParams2 = (FlexboxLayout.LayoutParams) cl2.getLayoutParams();
        layoutParams2.rightMargin = nMarginRight;
        cl2.setLayoutParams(layoutParams2);

        FlexboxLayout.LayoutParams layoutParams3 = (FlexboxLayout.LayoutParams) cl3.getLayoutParams();
        layoutParams3.rightMargin = nMarginRight;
        cl3.setLayoutParams(layoutParams3);

        FlexboxLayout.LayoutParams layoutParams4 = (FlexboxLayout.LayoutParams) cl4.getLayoutParams();
        layoutParams4.rightMargin = nMarginRight;
        cl4.setLayoutParams(layoutParams4);

        FlexboxLayout.LayoutParams layoutParams5 = (FlexboxLayout.LayoutParams) cl5.getLayoutParams();
        layoutParams5.rightMargin = nMarginRight;
        cl5.setLayoutParams(layoutParams5);


        FlexboxLayout.LayoutParams layoutParams11 = (FlexboxLayout.LayoutParams) cl11.getLayoutParams();
        layoutParams11.topMargin = nMarginRight;
        layoutParams11.rightMargin = nMarginRight * 2;
        cl11.setLayoutParams(layoutParams11);

        FlexboxLayout.LayoutParams layoutParams22 = (FlexboxLayout.LayoutParams) cl22.getLayoutParams();
        layoutParams22.topMargin = nMarginRight;
        layoutParams22.rightMargin = nMarginRight * 2;
        cl22.setLayoutParams(layoutParams22);

        FlexboxLayout.LayoutParams layoutParams33 = (FlexboxLayout.LayoutParams) cl33.getLayoutParams();
        layoutParams33.topMargin = nMarginRight;
        layoutParams33.rightMargin = nMarginRight * 2;
        cl33.setLayoutParams(layoutParams33);

        FlexboxLayout.LayoutParams layoutParams44 = (FlexboxLayout.LayoutParams) cl44.getLayoutParams();
        layoutParams44.topMargin = nMarginRight;
        layoutParams44.rightMargin = nMarginRight * 2;
        cl44.setLayoutParams(layoutParams44);

        FlexboxLayout.LayoutParams layoutParams55 = (FlexboxLayout.LayoutParams) cl55.getLayoutParams();
        layoutParams55.topMargin = nMarginRight;
        layoutParams55.rightMargin = nMarginRight * 2;
        cl55.setLayoutParams(layoutParams55);
    }
}