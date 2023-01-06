package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base2Adapter;
import java.util.List;

public class ListView2Activity extends AppCompatActivity {

    private static final String TAG = ListView2Activity.class.getSimpleName();

    private Button button1, button2, button3;

    private ListView lv1;
    private Base2Adapter base2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        lv1 = (ListView)findViewById(R.id.lv1);

        // CHOICE_MODE_NONE : 항목 선택 불가
        // CHOICE_MODE_SINGLE : 한개만 선택 가능
        // CHOICE_MODE_MULTIPLE : 여러개 선택 가능
        lv1.setChoiceMode(lv1.CHOICE_MODE_SINGLE);

        // 구분선 색
        lv1.setDivider(new ColorDrawable(Color.WHITE));

        //구분선
        lv1.setDividerHeight(0);

        base2Adapter = new Base2Adapter(this, MapApi.getInstance().getItem("서울"));
        lv1.setAdapter(base2Adapter);

        base2Adapter.setViewItemClickListener(new Base2Adapter.ViewItemClickListener() {
            @Override
            public void onViewItemClick(int nIndex) {

                base2Adapter.setSelectItem(nIndex);
                lv1.setAdapter(base2Adapter);

                Log.e(TAG, String.valueOf(nIndex));
            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                base2Adapter.setSelectItem(i);
                lv1.setAdapter(base2Adapter);

                itemData item = (itemData) base2Adapter.getItem(i);
                Log.e(TAG, item.getLocName());
            }
        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<itemData> item = MapApi.getInstance().getItem("서울");
                if(item != null){
                    base2Adapter.addAll(item);
                }
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<itemData> item = MapApi.getInstance().getItem("부산");
                if(item != null){
                    base2Adapter.addAll(item);
                }
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<itemData> item = MapApi.getInstance().getItem("대구");
                if(item != null){
                    base2Adapter.addAll(item);
                }
            }
        });

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

}