package com.daeseong.tablestyle_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class Main4Activity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private View tl1, tlmenu, clB1, clcontent;

    private ScrollView sv1;
    private HorizontalScrollView hs1;
    private RelativeLayout rl1;

    private int nCount = 200;
    private int nButtonWidth = 200;
    private int nButtonHeight = 100;

    private static int nlastX = 0, nlastY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitTitleBar();

        setContentView(R.layout.activity_main4);

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
        clcontent = (View)findViewById(R.id.clcontent);

        sv1 = (ScrollView)findViewById(R.id.sv1);
        hs1 = (HorizontalScrollView)findViewById(R.id.hs1);
        rl1 = (RelativeLayout)findViewById(R.id.rl1);

        sv1.setFadingEdgeLength(0);
        hs1.setFadingEdgeLength(0);
        sv1.setOnTouchListener(this);
        hs1.setOnTouchListener(this);
    }

    private void initData() {

        rl1.removeAllViews();

        rl1.setMinimumWidth(2200);
        rl1.setMinimumHeight(2200);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(nButtonWidth, nButtonHeight);
        params.setMargins(0, 0, 0, 0);

        for (int i = 0; i < nCount; i++) {

            String sNum =  String.format("%d", i);

            int nX = 0;
            int nY = 0;
            int nDiv = 10;

            if(i == 0){

                nY = 0;
                nX = 0;

            } else  {

                int nGapWidth = i % nDiv;
                int nGapHeight = i / nDiv;
                nX = nGapWidth * nButtonWidth;
                nY = nGapHeight * nButtonHeight;
            }

            Button btn = new Button(this);
            btn.setLayoutParams(params);
            btn.setBackgroundResource(R.drawable.btnbordero);
            btn.setEnabled(true);
            btn.setMinimumHeight(nButtonWidth);
            btn.setMinimumWidth(nButtonHeight);
            btn.setPadding(0, 0, 0, 0);
            btn.setX(nX);
            btn.setY(nY);
            btn.setTextSize(14);
            btn.setText(String.format("번호%d", i));
            btn.setOnClickListener(btnClick);
            btn.setOnTouchListener(btnTouch);
            rl1.addView(btn);
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

    private void changeScroll(int x, int y) {

        hs1.scrollBy(x, 0);
        sv1.scrollBy(0, y);
    }

    View.OnTouchListener btnTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){

                case MotionEvent.ACTION_MOVE:
                    int nX = (int) event.getRawX();
                    int nY = (int) event.getRawY();
                    changeScroll(nlastX - nX, nlastY - nY);
                    nlastX = nX;
                    nlastY = nY;
                    break;

                case MotionEvent.ACTION_UP:
                    break;

                default:
                    nlastX = (int) event.getRawX();
                    nlastY = (int) event.getRawY();
                    break;
            }
            return false;
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                int nX = (int) event.getRawX();
                int nY = (int) event.getRawY();
                changeScroll(nlastX - nX, nlastY - nY);
                nlastX = nX;
                nlastY = nY;
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                nlastX = (int) event.getRawX();
                nlastY = (int) event.getRawY();
                break;
        }

        nlastX = (int) event.getRawX();
        nlastY = (int) event.getRawY();
        return true;
    }
}