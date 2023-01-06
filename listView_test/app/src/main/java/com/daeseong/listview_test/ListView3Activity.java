package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListView3Activity extends AppCompatActivity {

    private static final String TAG = ListView3Activity.class.getSimpleName();

    private ListView lv1;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);

        lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = (String)adapterView.getItemAtPosition(i);
                Log.e(TAG, item);
            }
        });

        initData();

        //자동 클릭
        new Handler().post(new Runnable() {
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

    private void initData() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MapApi.getInstance().getList());
        lv1.setAdapter(adapter);
    }
}