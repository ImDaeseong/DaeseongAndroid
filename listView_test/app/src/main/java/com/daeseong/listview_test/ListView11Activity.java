package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base11Adapter;

public class ListView11Activity extends AppCompatActivity {

    private static final String TAG = ListView11Activity.class.getSimpleName();

    private ListView lv1;
    private Base11Adapter base11Adapter;

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view11);

        lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                base11Adapter.selectItem(i);

                itemData item = (itemData) base11Adapter.getItem(i);
                Log.e(TAG, item.getLocName());
            }
        });

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean[] selectedItems = base11Adapter.getSelectedItemList();
                for (int i = 0; i < selectedItems.length; i++) {

                    Log.e(TAG, i + " : " + selectedItems[i]);
                }
            }
        });

        initData();
    }

    private void initData() {

        base11Adapter = new Base11Adapter(this, MapApi.getInstance().getItem("서울"), lv1);
        lv1.setAdapter(base11Adapter);
    }
}