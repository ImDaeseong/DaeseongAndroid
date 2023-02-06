package com.daeseong.tablestyle_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private View tl1, tlmenu, clB1;

    private GridLayout gl;

    private int nRowCount = 20;
    private int nColumnCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitTitleBar();

        setContentView(R.layout.activity_main3);

        init();

        initConfiguration();

        initData();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Log.e(TAG, "가로 방향");

            tl1.setVisibility(View.GONE);
            tlmenu.setVisibility(View.GONE);
            clB1.setVisibility(View.GONE);

        } else if(newConfig.orientation== Configuration.ORIENTATION_PORTRAIT) {

            //Log.e(TAG, "세로 방향");

            tl1.setVisibility(View.VISIBLE);
            tlmenu.setVisibility(View.VISIBLE);
            clB1.setVisibility(View.VISIBLE);
        }
    }

    private void initConfiguration() {

        Configuration configuration = Resources.getSystem().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Log.e(TAG, "가로 방향");

            tl1.setVisibility(View.GONE);
            tlmenu.setVisibility(View.GONE);
            clB1.setVisibility(View.GONE);

        } else if(configuration.orientation== Configuration.ORIENTATION_PORTRAIT) {

            //Log.e(TAG, "세로 방향");

            tl1.setVisibility(View.VISIBLE);
            tlmenu.setVisibility(View.VISIBLE);
            clB1.setVisibility(View.VISIBLE);
        }
    }

    private void InitTitleBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(255, 255, 255));
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    private void init() {

        tl1 = (View) findViewById(R.id.tl1);
        tlmenu = (View)findViewById(R.id.tlmenu);
        clB1 = (View)findViewById(R.id.clB1);

        gl = (GridLayout) findViewById(R.id.gl);
    }

    private void initData() {

        gl.removeAllViews();

        gl.setRowCount(nRowCount);
        gl.setColumnCount(nColumnCount);

        int nIndex = 1;
        for(int i =0; i< nRowCount; i++){

            for(int k=0; k < nColumnCount; k++){

                Button btn = new Button(this);
                btn.setPadding(0,0,0,0);
                btn.setId(nIndex);
                btn.setBackgroundResource(R.drawable.btnbordero);
                btn.setTextColor(Color.BLACK);
                btn.setTextSize(14);
                btn.setText(String.format("번호%d", nIndex));
                btn.setOnClickListener(btnClick);
                nIndex++;
                gl.addView(btn);
            }
        }

    }

    //버튼 클릭 이벤트
    View.OnClickListener btnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            //Log.e(TAG, String.valueOf(v.getId()));

            //버튼 텍스트
            Button btn = ((Button) v);
            String sText = btn.getText().toString();
            //Log.e(TAG, sText);
        }
    };
}