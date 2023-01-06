package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import com.daeseong.listview_test.Model.Base6Adapter;
import com.daeseong.listview_test.Model.Base6Adapter1;
import java.util.List;

public class ListView6Activity extends AppCompatActivity {

    private static final String TAG = ListView6Activity.class.getSimpleName();

    private GridView gv1;
    private Base6Adapter base6Adapter;

    private ListView lv1;
    private Base6Adapter1 base6Adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view6);

        lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setDivider(new ColorDrawable(Color.WHITE));
        lv1.setDividerHeight(0);
        lv1.setPadding(0, 0, 0, 0);

        base6Adapter1 = new Base6Adapter1(this, MapApi.getInstance().getItem("서울"));
        lv1.setAdapter(base6Adapter1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                base6Adapter1.setSelectItem(i);
                lv1.setAdapter(base6Adapter1);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"));
                startActivity(intent);
            }
        });

        gv1 = (GridView)findViewById(R.id.gv1);
        gv1.setSelector(R.color.purple_200);
        gv1.setHorizontalSpacing(0);
        gv1.setVerticalSpacing(0);
        gv1.setGravity(Gravity.CENTER);
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                List<itemData> item = MapApi.getInstance().getItem(MapApi.getInstance().getList().get(i));
                if(item != null){
                    base6Adapter1.addAll(item);
                }

                view.setSelected(true);
            }
        });

        base6Adapter = new Base6Adapter(this, MapApi.getInstance().getList());
        gv1.setAdapter(base6Adapter);


        //자동 클릭
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                try {

                    gv1.performItemClick(gv1.getChildAt(0), 0, 0);

                }catch (Exception ex){
                    ex.getMessage().toString();
                }
            }
        });

    }

}