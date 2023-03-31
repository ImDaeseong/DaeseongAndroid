package com.daeseong.paging_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.daeseong.paging_test.API.SearchApi;
import com.daeseong.paging_test.Common.DateTime;
import com.daeseong.paging_test.Common.GetStringTask;
import com.daeseong.paging_test.Model.Recycler5Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private FloatingActionButton fb1;

    private SwipeRefreshLayout swl1;
    private RecyclerView rv1;
    private RecyclerView.LayoutManager manager;
    private Recycler5Adapter adapter;
    private int nLastPos = 0;

    private String sResult = "";

    private int nTotalPage = 0;    // 총 페이지
    private int nTotalCount = 0;   // 데이터 개수
    private int nPageSize = 10;    // 페이지에 보여줄 개수

    private String sUrl = "";
    private String sSearchkey = "android";
    private int nIndex = 0;

    private HandlerThread thread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        initThread();

        initTotalpage();

        initData();

        fb1 = findViewById(R.id.fb1);

        swl1 = findViewById(R.id.swl1);

        rv1 = findViewById(R.id.rv1);

        manager = new LinearLayoutManager(this);
        rv1.setLayoutManager(manager);

        //adapter = new Recycler5Adapter(this);
        adapter = new Recycler5Adapter(this, SearchApi.getInstance().getItem(nIndex));
        rv1.setAdapter(adapter);


        rv1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState){
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        //Log.e(TAG, "스크롤 상태가 드래그 될때");
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //Log.e(TAG, "스크롤이 정지되어 있는 상태");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        //Log.e(TAG, "위든 아래든 마지막 위치에 도달");
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int nlastItem = ((LinearLayoutManager) rv1.getLayoutManager()).findLastCompletelyVisibleItemPosition()+1;
                int nTotalCount = rv1.getAdapter().getItemCount();

                if(nlastItem == nTotalCount){

                    Log.e(TAG, "스크롤 마지막에 도착");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            fb1.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });

        swl1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        swl1.setRefreshing(true);

                        ++nIndex;

                        if(nIndex > nTotalPage){
                            nIndex = nTotalPage;
                            swl1.setRefreshing(false);
                        }

                        sUrl = String.format("%s&q=%s:created:>%s&PAGE=%d", ConstantsUrl.sUrl2, sSearchkey, DateTime.getOneDayago(), nIndex);
                        sResult = new GetStringTask().execute(sUrl);
                        parsingData(nIndex, sResult);

                        swl1.setRefreshing(false);
                    }
                });

            }
        });

        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<SearchApi.itemData> list = SearchApi.getInstance().getSearch("Android");
                if(list != null){
                    adapter.add(list);
                }
                list.clear();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SearchApi.getInstance().clear();

        StopThread();
    }

    private void initThread(){

        try {

            thread = new HandlerThread("paging");
            thread.start();

            handler = new Handler(thread.getLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                }
            };

        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    private void StopThread(){

        try {

            if(thread != null){
                thread.getLooper().quit();
                thread.quit();
            }

        }catch (Exception ex){
            ex.getMessage().toString();
        }
        finally {
            handler = null;
            thread = null;
        }
    }

    private void initTotalpage(){

        handler.post(new Runnable() {
            @Override
            public void run() {

                try {

                    sUrl = String.format("%s&q=%s:created:>%s", ConstantsUrl.sUrl1, sSearchkey, DateTime.getOneDayago());
                    sResult = new GetStringTask().execute(sUrl);
                    if (!TextUtils.isEmpty(sResult)){

                        JSONObject jsonObject = new JSONObject(sResult);
                        if(jsonObject.has("total_count")) {
                            nTotalCount = jsonObject.getInt("total_count");
                        }
                    }

                }catch (Exception ex){
                    Log.e(TAG, ex.getMessage().toString());
                }

                //전체 페이지 계산
                nTotalPage = (nTotalCount/nPageSize) + 1;
                Log.e(TAG, "총 페이지:" + String.valueOf(nTotalPage) + " 데이터 개수:" + nTotalCount);
            }
        });
    }

    private void initData(){

        handler.post(new Runnable() {
            @Override
            public void run() {

                ++nIndex;
                sUrl = String.format("%s&q=%s:created:>%s&PAGE=%d", ConstantsUrl.sUrl2, sSearchkey, DateTime.getOneDayago(), nIndex);
                sResult = new GetStringTask().execute(sUrl);
                parsingData(nIndex, sResult);
            }
        });
    }

    private void parsingData(int nIndex, String sResult) {

        try {

            if (!TextUtils.isEmpty(sResult)) {

                JSONObject jsonObject = new JSONObject(sResult);

                List<SearchApi.itemData> list = new ArrayList<>();

                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int j = 0; j < jsonArray.length(); j++){

                    JSONObject JSonObj = jsonArray.getJSONObject(j);

                    String ID = "";
                    if(JSonObj.has("id"))
                        ID = JSonObj.getString("id");

                    String NAME = "";
                    if(JSonObj.has("name"))
                        NAME = JSonObj.getString("name");

                    String Createdat = "";
                    if(JSonObj.has("created_at"))
                        Createdat = JSonObj.getString("created_at");

                    String HTMLURL = "";
                    if(JSonObj.has("owner")){

                        String sOwner = JSonObj.getString("owner");
                        JSONObject jsonObj = new JSONObject(sOwner);

                        if(jsonObj.has("html_url")) {
                            HTMLURL = jsonObj.getString("html_url");
                        }
                    }

                    SearchApi.itemData item = new SearchApi.itemData();
                    item.setItem(ID, NAME, HTMLURL, Createdat);
                    list.add(item);
                }

                if(SearchApi.getInstance().setMap(nIndex, list) ){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adapter.addAll(list);
                        }
                    });
                }
            }
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }
}