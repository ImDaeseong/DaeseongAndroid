package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base1Adapter;
import java.util.ArrayList;
import java.util.HashMap;

public class ListView1Activity extends AppCompatActivity {

    private static final String TAG = ListView1Activity.class.getSimpleName();

    private Button button1, button2, button3, button4;

    private ListView lv1;
    private Base1Adapter base1Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);

        lv1 = (ListView)findViewById(R.id.lv1);

        // CHOICE_MODE_NONE : 항목 선택 불가
        // CHOICE_MODE_SINGLE : 한개만 선택 가능
        // CHOICE_MODE_MULTIPLE : 여러개 선택 가능
        lv1.setChoiceMode(lv1.CHOICE_MODE_SINGLE);

        // 구분선 색
        lv1.setDivider(new ColorDrawable(Color.WHITE));

        //구분선
        lv1.setDividerHeight(0);

        lv1.setPadding(0, 0, 0, 0);

        //lv1.setBackgroundColor(Color.TRANSPARENT);

        base1Adapter = new Base1Adapter(this, MapApi.getInstance().getItem("서울"));
        lv1.setAdapter(base1Adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                base1Adapter.setSelectItem(i);
                lv1.setAdapter(base1Adapter);

                itemData item = (itemData) base1Adapter.getItem(i);
                Log.e(TAG, item.getLocName());
            }
        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<itemData> item = MapApi.getInstance().getItem("서울");
                if(item != null){
                    base1Adapter.addAll(item);
                }
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<itemData> item = MapApi.getInstance().getItem("부산");
                if(item != null){
                    base1Adapter.addAll(item);
                }
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<itemData> item = MapApi.getInstance().getItem("대구");
                if(item != null){
                    base1Adapter.addAll(item);
                }
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, ArrayList<itemData>> map = MapApi.getInstance().getItem();
                for (String key : map.keySet()) {
                    ArrayList<itemData> list = map.get(key);
                    if (list != null) {
                        base1Adapter.add(list);
                    }
                }
            }
        });

        //자동 클릭
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                try {

                    lv1.performItemClick(lv1, 0, 0);

                }catch (Exception ex){
                    ex.getMessage().toString();
                }
            }
        });

    }
}