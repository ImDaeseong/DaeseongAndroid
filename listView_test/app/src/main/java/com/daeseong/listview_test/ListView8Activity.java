package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base1Adapter;

public class ListView8Activity extends AppCompatActivity {

    private static final String TAG = ListView8Activity.class.getSimpleName();

    private SwipeRefreshLayout swl1;
    private ListView lv1;
    private View FooterView;
    private Base1Adapter base1Adapter;

    private int nIndex = 0;
    private boolean isBottom = false;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view8);

        swl1 = findViewById(R.id.swl1);

        lv1 = findViewById(R.id.lv1);

        //리스트 하단바
        FooterView = getLayoutInflater().inflate(R.layout.listview_bottom,null);
        lv1.addFooterView(FooterView);
        FooterView.setVisibility(View.GONE);

        handler.post(new Runnable() {
            @Override
            public void run() {
                base1Adapter = new Base1Adapter(ListView8Activity.this, MapApi.getInstance().getItem("서울"));
                lv1.setAdapter(base1Adapter);
            }
        });

        swl1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swl1.setRefreshing(true);

                ++nIndex;

                if(nIndex > MapApi.getInstance().getList().size()){
                    nIndex = 0;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String sKey = MapApi.getInstance().getList().get(nIndex);
                        base1Adapter.add( MapApi.getInstance().getItem(sKey));
                        swl1.setRefreshing(false);
                    }
                });
            }
        });

        lv1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0){

                    if (isBottom == false){

                        FooterView.setVisibility(View.VISIBLE);
                        isBottom = true;
                    }

                } else {
                    isBottom = false;
                }

                if(!isBottom && FooterView.getVisibility() == View.VISIBLE){
                    FooterView.setVisibility(View.GONE);
                }
            }
        });
    }
}