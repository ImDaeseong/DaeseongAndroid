package com.daeseong.gridlayoutmanager_test;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Gridlayout1Activity extends AppCompatActivity {

    private static final String TAG = Gridlayout1Activity.class.getSimpleName();

    private RecyclerView rv1;
    private RecyclerViewAdapter adapter;
    private View v2;

    ArrayList<Item> list = new ArrayList<Item>() {{
        add(new Item("image1",R.drawable.a));
        add(new Item("image2",R.drawable.a));
        add(new Item("image3",R.drawable.a));
        add(new Item("image4",R.drawable.a));
        add(new Item("image5",R.drawable.a));
        add(new Item("image6",R.drawable.a));
        add(new Item("image7",R.drawable.a));
        add(new Item("image8",R.drawable.a));
        add(new Item("image9",R.drawable.a));
        add(new Item("image10",R.drawable.a));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayout1);

        rv1 = findViewById(R.id.rv1);
        adapter  = new RecyclerViewAdapter(getApplicationContext(), list);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rv1.setLayoutManager(layoutManager);


        // 간격 추가
        int spacingInPixels = 20;
        rv1.addItemDecoration(new GridSpacingItem(2, spacingInPixels, true));

        rv1.setAdapter(adapter);


        //동적 추가
        v2 = findViewById(R.id.v2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = list.size() + 1;
                list.add(new Item("image" + index, R.drawable.a));
                adapter.notifyItemInserted(index);
            }
        });

    }
}