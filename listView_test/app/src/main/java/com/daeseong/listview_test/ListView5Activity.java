package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListView5Activity extends AppCompatActivity {

    private static final String TAG = ListView5Activity.class.getSimpleName();

    private Button button1, button2, button3;

    private ListView lv1;
    private FloatingActionButton fb1;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view5);

        fb1 = findViewById(R.id.fb1);

        lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = (String)adapterView.getItemAtPosition(i);
                Log.e(TAG, item);
            }
        });

        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int k=0; k < lv1.getAdapter().getCount(); k++){

                    if(lv1.isItemChecked(k)){
                        Log.e(TAG, (String) lv1.getItemAtPosition(k));
                    }
                }
            }
        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MapApi.getInstance().addList("서울1");
                adapter.notifyDataSetChanged();
                Log.e(TAG, String.valueOf(adapter.getCount()));
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.getCount() > 0){

                    int nSelect = lv1.getCheckedItemPosition();
                    if(nSelect > -1) {
                        MapApi.getInstance().updateList(nSelect, "서울1-1");
                        adapter.notifyDataSetChanged();
                        Log.e(TAG, String.valueOf(adapter.getCount()));
                    }
                }
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.getCount() > 0){

                    int nSelect = lv1.getCheckedItemPosition();
                    if(nSelect > -1) {
                        MapApi.getInstance().removeList(nSelect);
                        adapter.notifyDataSetChanged();
                        Log.e(TAG, String.valueOf(adapter.getCount()));
                    }
                }
            }
        });

        initData();
    }

    private void initData() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, MapApi.getInstance().getList());
        lv1.setAdapter(adapter);
    }
}