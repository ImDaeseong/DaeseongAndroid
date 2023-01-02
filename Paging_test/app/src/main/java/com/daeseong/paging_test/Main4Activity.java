package com.daeseong.paging_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import com.daeseong.paging_test.API.SearchApi;
import com.daeseong.paging_test.Common.DateTime;
import com.daeseong.paging_test.Common.GetStringTask;
import com.daeseong.paging_test.Model.Base4Adapter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private SwipeRefreshLayout swl1;
    private ListView lv1;
    private Base4Adapter base4Adapter;

    private String sResult = "";

    private int nTotalPage = 0;    // 총 페이지
    private int nTotalCount = 0;   // 데이터 개수
    private int nPageSize = 10;    // 페이지에 보여줄 개수

    private String sUrl = "";
    private String sSearchkey = "android";
    private int nIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        initTotalpage();

        swl1 = findViewById(R.id.swl1);

        lv1 = findViewById(R.id.lv1);

        base4Adapter = new Base4Adapter();
        lv1.setAdapter(base4Adapter);

        lv1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i){
                    case SCROLL_STATE_IDLE:
                        //Log.e(TAG, "스크롤 이동하다가 멈추었을때");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        //Log.e(TAG, "스크롤 터치되어 있을 때");
                        break;
                    case SCROLL_STATE_FLING:
                        //Log.e(TAG, "스크롤 움직이고 있을때");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                //Log.e(TAG, "현재 index:" + i);
                //Log.e(TAG, "화면에 보이는 리스트 데이터 개수:" + i1);
                //Log.e(TAG, "리스트 총 데이터 개수:" + i2);

                int nTotalCount = i2;
                int nlastItem = lv1.getLastVisiblePosition() + 1;
                if(nlastItem == nTotalCount){
                    Log.e(TAG, "스크롤 마지막에 도착");
                }

                /*
                if(i > nLastPos){
                    Log.e(TAG, "up");
                }else if(i < nLastPos){
                    Log.e(TAG, "down");
                }
                nLastPos = i;
                */
            }
        });

        swl1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swl1.setRefreshing(true);

                ++nIndex;

                if(nIndex > nTotalPage){
                    nIndex = nTotalPage;
                    swl1.setRefreshing(false);
                    return;
                }

                sUrl = String.format("%s&q=%s:created:>%s&PAGE=%d", ConstantsUrl.sUrl2, sSearchkey, DateTime.getOneDayago(), nIndex);
                sResult = new GetStringTask().execute(sUrl);
                parsingData(nIndex, sResult);

                swl1.setRefreshing(false);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SearchApi.getInstance().clear();
    }

    private void initTotalpage(){

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
        //Log.e(TAG, "총 페이지:" + String.valueOf(nTotalPage) + " 데이터 개수:" + nTotalCount);
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

                    base4Adapter.addAll(list);
                }
            }
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

}