package com.daeseong.swiperefreshlayout_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity4 extends AppCompatActivity {

    private static final String TAG = MainActivity4.class.getSimpleName();

    private SwipeRefreshLayoutEx swipeRefreshLayoutEx;

    private ConstraintLayoutEx waitConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        waitConstraintLayout = (ConstraintLayoutEx)findViewById(R.id.wait);

        swipeRefreshLayoutEx = (SwipeRefreshLayoutEx)findViewById(R.id.swLayout);
        swipeRefreshLayoutEx.hideProgressBar(true);
        swipeRefreshLayoutEx.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayoutEx.setRefreshing(true);

                waitConstraintLayout.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshLayoutEx.isRefreshing()) {
                            swipeRefreshLayoutEx.setRefreshing(false);
                        }

                        requestData();

                        waitConstraintLayout.hide();
                    }
                }, 1000);
            }
        });
    }

    public void requestData(){

        Log.e(TAG, "업데이트 완료");
    }
}