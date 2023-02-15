package com.im.daeseong.horizontalscrollview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HorScroll3Activity extends AppCompatActivity {

    private static final String TAG = HorScroll3Activity.class.getSimpleName();

    private View cL3;
    private LinearLayout LL1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_scroll3);

        init();
        initData();
    }

    private void init() {

        LL1 = (LinearLayout) findViewById(R.id.LL1);

        cL3 = (View)findViewById(R.id.cL3);
        cL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initData() {

        for (int i = 0; i < 10; i++) {

            ConstraintLayout clview = (ConstraintLayout) getLayoutInflater().inflate(R.layout.image_scroll_item, null);

            clview.setTag(i);
            clview.setOnClickListener(cLClicked);

            //이미지
            ImageView ivImg = (ImageView) clview.findViewById(R.id.ivreservation);

            //마진 설정
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, UnitUtils.dip2px(this, 6), 0);
            clview.setLayoutParams(params);

            //추가
            LL1.addView(clview);
        }
    }

    View.OnClickListener cLClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {

                int nIndex = (int)v.getTag();
                Log.e(TAG, String.valueOf(nIndex));

            } catch (Exception ex) {
            }
        }
    };
}