package com.daeseong.paging_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
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
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import com.daeseong.paging_test.API.SearchApi;
import com.daeseong.paging_test.Common.DateTime;
import com.daeseong.paging_test.Common.HttpUtil;
import com.daeseong.paging_test.Model.BaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main6Activity.class.getSimpleName();

    private SwipeRefreshLayout swl1;
    private NestedScrollView nsv1;

    private FloatingActionButton fb1;

    private RecyclerView rv1;
    private RecyclerView.LayoutManager manager;
    private BaseRecyclerAdapter adapter;
    private ProgressBar progressbar1;

    private int nTotalPage = -1;   // 총 페이지
    private int nTotalCount = 0;  // 데이터 개수
    private int nIndex = 0;       // 현재 페이지
    private int nPageSize = 1;    // 페이지에 보여줄 개수
    private String sSearchkey = "android";

    private HandlerThread thread;
    private Handler handler;

    //paging 처리
    public static final int RESULT_PASING_SEARCH = 1;
    public static final int RESULT_PASING_SEARCHEND = 2;

    private List<SearchApi.itemData> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        initThread();


        progressbar1 = (ProgressBar)findViewById(R.id.progressbar1);
        progressbar1.setVisibility(View.GONE);


        //당겨서 새로고침
        swl1 = (SwipeRefreshLayout)findViewById(R.id.swl1);
        swl1.setDistanceToTriggerSync(500);//스와이프 민감도 설정(기본:120)
        swl1.setColorSchemeColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white), getResources().getColor(R.color.white));
        swl1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {

                    swl1.setRefreshing(true);

                    //데이터 초기화
                    nTotalPage = -1;
                    nTotalCount = 0;
                    nIndex = 0;
                    adapter.clear();
                    requestSearchPaging();

                    if (swl1.isRefreshing()) {
                        swl1.setRefreshing(false);
                    }

                } catch (Exception ex) {
                }
            }
        });

        nsv1 = (NestedScrollView) findViewById(R.id.nsv1);
        nsv1.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                if (nsv1 != null) {
                    if (nsv1.getScrollY() > 0 ) {
                        fb1.setVisibility(View.VISIBLE);
                    } else {
                        fb1.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        fb1 = (FloatingActionButton)findViewById(R.id.fb1);
        fb1.setVisibility(View.INVISIBLE);
        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollTop();
            }
        });

        rv1 = (RecyclerView)findViewById(R.id.rv1);
        manager = new LinearLayoutManager(this);
        rv1.setLayoutManager(manager);

        adapter = new BaseRecyclerAdapter(this);
        rv1.setAdapter(adapter);

        rv1.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (swl1.isRefreshing()) {
                    return;
                }

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    requestSearchPaging();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {

            list.clear();
            StopThread();

        } catch (Exception ex) {
        }
    }

    public void scrollTop() {

        try {
            nsv1.scrollTo(0, 0);
        } catch (Exception ex) {
        }
    }

    private void initThread() {

        try {

            thread = new HandlerThread("Main6Activity_paging");
            thread.start();

            handler = new Handler(thread.getLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    switch ((msg.what)){

                        case RESULT_PASING_SEARCH://조회
                            break;

                        case RESULT_PASING_SEARCHEND://조회 완료
                            resultEndPaging();
                            break;

                        default:
                            break;
                    }
                }
            };

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private void StopThread() {

        try {
            if(thread != null){
                thread.getLooper().quit();
                thread.quit();
            }
        } catch (Exception ex) {
        } finally {
            handler = null;
            thread = null;
        }
    }

    //조회
    private void requestSearchPaging() {

        try {

            handler.post(new Runnable() {
                @Override
                public void run() {

                    //데이터 조회시 프로그래스 보임
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressbar1.setVisibility(View.VISIBLE);
                        }
                    });

                    ++nIndex;

                    String sUrl = String.format("%s&q=%s:created:>%s&PAGE=%d", ConstantsUrl.sUrl3, sSearchkey, DateTime.getOneDayago(), nIndex);
                    //Log.e(TAG, sUrl);

                    String sResult = HttpUtil.GetDataString(sUrl);
                    //Log.e(TAG, sResult);

                    //데이터 조회 완료시 프로그래스 숨김
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressbar1.setVisibility(View.GONE);
                        }
                    });

                    //Log.e(TAG, "데이터 조회 종료");
                    try {
                        if (nTotalPage > -1) {
                            if (nIndex > nTotalPage) {
                                handler.sendEmptyMessage(RESULT_PASING_SEARCHEND);
                                return;
                            }
                        }
                    } catch (Exception ex) {
                    }

                    //Log.e(TAG, "데이터 조회");
                    try {

                        if (!TextUtils.isEmpty(sResult)) {

                            JSONObject jsonObject = new JSONObject(sResult);

                            nTotalCount = jsonObject.getInt("total_count");
                            nTotalPage = (nTotalCount/nPageSize) + 1;

                            JSONArray jsonArray = jsonObject.getJSONArray("items");
                            for(int i =0; i < jsonArray.length(); i++) {

                                if (list == null) {
                                    list = new ArrayList<>();
                                } else {
                                    list.clear();
                                }

                                JSONObject JSonObj = jsonArray.getJSONObject(i);

                                String ID = "";
                                if(JSonObj.has("id")) {
                                    ID = JSonObj.getString("id");
                                }

                                String NAME = "";
                                if(JSonObj.has("name")) {
                                    NAME = JSonObj.getString("name");
                                }

                                String CreateData = "";
                                if(JSonObj.has("created_at")) {
                                    CreateData = JSonObj.getString("created_at");
                                }

                                String HTMLURL = "";
                                if(JSonObj.has("owner")){
                                    String sOwner = JSonObj.getString("owner");
                                    JSONObject jsonObj = new JSONObject(sOwner);
                                    if(jsonObj.has("html_url")) {
                                        HTMLURL = jsonObj.getString("html_url");
                                    }
                                }

                                SearchApi.itemData item = new SearchApi.itemData();
                                item.setItem(ID, NAME, HTMLURL, CreateData);
                                list.add(item);
                            }
                        }

                    } catch (Exception ex) {
                    }

                    //BaseRecyclerAdapter 추가
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                if (nTotalCount == 0) {

                                    //Log.e(TAG, "데이터 조회 0 인 경우");
                                    handler.sendEmptyMessage(RESULT_PASING_SEARCHEND);
                                } else {

                                    //Log.e(TAG, "조회 데이터 추가");
                                    adapter.addAll(list);
                                }

                            } catch (Exception ex) {
                            }
                        }
                    });

                }
            });

        } catch (Exception ex) {
        }
    }

    //조회 완료
    private void resultEndPaging() {

        try {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    nTotalPage = -1;   // 총 페이지
                    nTotalCount = 0;  // 데이터 개수
                    nIndex = 0;

                    progressbar1.setVisibility(View.GONE);
                }
            });

        } catch (Exception ex) {
        }
    }

}