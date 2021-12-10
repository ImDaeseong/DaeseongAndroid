package com.daeseong.swiperefreshlayout_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity1 extends AppCompatActivity {

    private static final String TAG = MainActivity1.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;

    private View wait;
    private ImageView iv1;
    private Animation slide_down, loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        wait = findViewById(R.id.wait);
        iv1 = (ImageView)wait.findViewById(R.id.iv1);

        slide_down = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        loading = AnimationUtils.loadAnimation(this, R.anim.loading);


        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500);

        //스와이프 민감도 설정(기본:120)
        //swipeRefreshLayout.setDistanceToTriggerSync(20);

        //swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

        //스와이프 프로그래스바 위치 - 숨김
        swipeRefreshLayout.setProgressViewOffset(true, -10000, -10000);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(true);

                wait.startAnimation(slide_down);
                wait.setVisibility(View.VISIBLE);

                iv1.startAnimation(loading);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }

                        requestData();

                        wait.setVisibility(View.GONE);
                        wait.clearAnimation();

                        iv1.clearAnimation();
                    }
                }, 1000);
            }
        });
    }

    public void requestData(){

        Log.e(TAG, "업데이트 완료");
    }

}