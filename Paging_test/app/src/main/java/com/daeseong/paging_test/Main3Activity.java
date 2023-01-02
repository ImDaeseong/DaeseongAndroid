package com.daeseong.paging_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daeseong.paging_test.API.SearchApi;
import com.daeseong.paging_test.Common.DateTime;
import com.daeseong.paging_test.Common.GetStringTask;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private String sResult = "";

    private int nTotalPage = 0;    // 총 페이지
    private int nTotalCount = 0;   // 데이터 개수
    private int nPageSize = 10;    // 페이지에 보여줄 개수

    private String sUrl = "";
    private String sSearchkey = "android";

    private HandlerThread thread;
    private Handler handler;
    private Handler reshandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initThread();

        initTotalpage();

        tv1 = (TextView) findViewById(R.id.tv1);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    for (int i = 1; i <= nTotalPage; i++) {
                        handler.sendEmptyMessageDelayed(i, i);
                    }

                }catch (Exception ex){
                    ex.getMessage().toString();
                }

            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parsingDataResult(1);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parsingDataResult(2);
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parsingDataResult(3);
            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parsingDataResult(4);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        StopThread();
    }

    private void initThread(){

        try {

            thread = new HandlerThread("paging");
            thread.start();
            handler = new Handler(thread.getLooper(), new PageCallback());

            reshandler = new Handler(getMainLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    Log.e(TAG, String.valueOf(msg.what));
                    Log.e(TAG, (String) msg.obj);
                }
            };

        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    private void StopThread(){

        try {

            if(reshandler != null){
                reshandler.removeMessages(0);
            }

            if(handler != null) {
                handler.removeCallbacks(initTotalpage);
            }

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

        handler.postDelayed(initTotalpage, 10);
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

                SearchApi.getInstance().setMap(nIndex, list);
            }
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private void parsingDataResult(int nIndex){

        HashMap<Integer, List<SearchApi.itemData>> map = SearchApi.getInstance().getItem();

        if(nIndex == 1) {

            for (Integer key : map.keySet()) {
                List<SearchApi.itemData> list = map.get(key);
                if (list != null) {
                    for (SearchApi.itemData i : list) {
                        Log.e(TAG, key + " " + i.NAME);
                    }
                }
            }

        } else if(nIndex == 2) {

            for(Integer key : map.keySet()){
                for (int i = 0; i < map.get(key).size(); i++) {
                    Log.e(TAG, key + " " + map.get(key).get(i).NAME);
                }
            }

        } else if(nIndex == 3) {

            int nKey = 2;
            List<SearchApi.itemData> list = SearchApi.getInstance().getItem(nKey);
            if(list != null) {
                for (SearchApi.itemData i : list) {
                    Log.e(TAG, nKey + " " + i.NAME);
                }
            }

        } else if(nIndex == 4) {

            for (Integer key : map.keySet()) {
                List<SearchApi.itemData> list = map.get(key);
                if (list != null) {
                    for (SearchApi.itemData i : list) {

                        if(i.NAME.toString().indexOf("Android") > -1){
                            Log.e(TAG, key + " " + i.NAME);
                        }
                    }
                }
            }

        }
    }

    private Runnable initTotalpage = new Runnable() {
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
    };


    class PageCallback implements Handler.Callback {

        @Override
        public boolean handleMessage(@NonNull Message message) {

            try {

                int i = message.what;
                sUrl = String.format("%s&q=%s:created:>%s&PAGE=%d", ConstantsUrl.sUrl2, sSearchkey, DateTime.getOneDayago(), i);
                sResult = new GetStringTask().execute(sUrl);
                parsingData(i, sResult);

                Message msg = new Message();
                msg.what = message.what;
                msg.obj = sResult;
                reshandler.sendMessage(msg);

            }catch (Exception ex){
                ex.getMessage().toString();
            }

            return false;
        }
    }
}