package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base10Adapter;

public class ListView10Activity extends AppCompatActivity {

    private static final String TAG = ListView10Activity.class.getSimpleName();

    private ListView lv1;
    private Base10Adapter base10Adapter;

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
                Log.e(TAG, item.getLocName());
            }
        });

        initData();
    }

    private void initData() {

        base10Adapter = new Base10Adapter(this, MapApi.getInstance().getItem("서울"));
        lv1.setAdapter(base10Adapter);
    }
}