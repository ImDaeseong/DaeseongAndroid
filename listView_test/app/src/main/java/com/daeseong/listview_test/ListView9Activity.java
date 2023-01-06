package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base9Adapter;
import com.daeseong.listview_test.Model.Base9Adapter1;
import java.util.ArrayList;

public class ListView9Activity extends AppCompatActivity {

    private static final String TAG = ListView9Activity.class.getSimpleName();

    private ListView lv1, lv2;
    private Base9Adapter base9Adapter;
    private Base9Adapter1 base9Adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view9);

        lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lv1.setDivider(new ColorDrawable(Color.WHITE));
        lv1.setDividerHeight(0);
        lv1.setPadding(10, 10, 10, 10);

        lv2 = (ListView)findViewById(R.id.lv2);
        lv2.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lv2.setDivider(new ColorDrawable(Color.WHITE));
        lv2.setDividerHeight(0);
        lv2.setPadding(10, 10, 10, 10);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String sKey = MapApi.getInstance().getList().get(i);
                ArrayList<itemData> item =  MapApi.getInstance().getItem(sKey);
                if(item != null){
                    base9Adapter1.addAll(item);
                }
            }
        });

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                itemData item = (itemData) base9Adapter1.getItem(i);
                Log.e(TAG, item.getLocName());

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"));
                startActivity(intent);
            }
        });

        initData();
    }

    private void initData() {

        base9Adapter = new Base9Adapter(this, MapApi.getInstance().getList());
        lv1.setAdapter(base9Adapter);

        //1번째 선택
        lv1.setItemChecked(0, true);

        base9Adapter1 = new Base9Adapter1(this, MapApi.getInstance().getItem("서울"));
        lv2.setAdapter(base9Adapter1);
    }
}