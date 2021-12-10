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

public class MainActivity3 extends AppCompatActivity {

    private static final String TAG = MainActivity3.class.getSimpleName();

    private SwipeRefreshLayoutEx swipeRefreshLayoutEx;

    private View wait;
    private ImageView iv1;
    private Animation slide_down, loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        wait = findViewById(R.id.wait);
        iv1 = (ImageView)wait.findViewById(R.id.iv1);

        slide_down = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        loading = AnimationUtils.loadAnimation(this, R.anim.loading);

        swipeRefreshLayoutEx = (SwipeRefreshLayoutEx)findViewById(R.id.swLayout);
        swipeRefreshLayoutEx.hideProgressBar(true);
        swipeRefreshLayoutEx.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayoutEx.setRefreshing(true);

                wait.startAnimation(slide_down);
                wait.setVisibility(View.VISIBLE);

                iv1.startAnimation(loading);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshLayoutEx.isRefreshing()) {
                            swipeRefreshLayoutEx.setRefreshing(false);
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