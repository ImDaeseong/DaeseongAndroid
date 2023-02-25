package com.im.daeseong.horizontalscrollview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HorScroll4Activity extends AppCompatActivity {

    private static final String TAG = HorScroll4Activity.class.getSimpleName();

    private View cl1, cl2, cl3, cl4, cl5, cl6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_scroll4);

        cl1 = (View)findViewById(R.id.cl1);
        cl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "cl1");
            }
        });

        cl2 = (View)findViewById(R.id.cl2);
        cl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "cl2");
            }
        });

        cl3 = (View)findViewById(R.id.cl3);
        cl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "cl3");
            }
        });

        cl4 = (View)findViewById(R.id.cl4);
        cl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "cl4");
            }
        });

        cl5 = (View)findViewById(R.id.cl5);
        cl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "cl5");
            }
        });

        cl6 = (View)findViewById(R.id.cl6);
        cl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "cl6");
            }
        });
    }
}