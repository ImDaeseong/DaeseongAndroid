package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base10Adapter;

import java.util.HashMap;

public class ListView10Activity extends AppCompatActivity {

    private static final String TAG = ListView10Activity.class.getSimpleName();

    private ListView lv1;
    private Base10Adapter base10Adapter;

    private Button btn1;

    private HashMap<Integer, Boolean> checkMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view10);

        lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv1.setDivider(new ColorDrawable(Color.WHITE));
        lv1.setDividerHeight(0);
        lv1.setPadding(10, 10, 10, 10);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                itemData item = (itemData) base10Adapter.getItem(i);
                setCheckList(i);
                Log.e(TAG, item.getLocName());
            }
        });

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0; i < checkMap.size(); i++) {
                    Log.e(TAG, i + ":" + checkMap.get(i).toString());
                }
            }
        });

        initData();
    }

    private void initData() {

        base10Adapter = new Base10Adapter(this, MapApi.getInstance().getItem("서울"));
        lv1.setAdapter(base10Adapter);
    }

    //저장된 체크리스트
    private void setCheckList(int nSelect) {

        if ( checkMap.containsKey(nSelect) ) {

            if ( checkMap.get(nSelect) == true) {
                checkMap.put(nSelect, false);
            } else {
                checkMap.put(nSelect, true);
            }
        } else {
            checkMap.put(nSelect, true);
        }
    }
}