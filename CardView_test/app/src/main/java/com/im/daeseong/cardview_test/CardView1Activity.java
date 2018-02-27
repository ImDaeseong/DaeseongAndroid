package com.im.daeseong.cardview_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardView1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view1);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Item> items = new ArrayList<>();
        Item[] item = new Item[5];
        item[0] = new Item(R.drawable.banner1, "#1");
        item[1] = new Item(R.drawable.banner2, "#2");
        item[2] = new Item(R.drawable.banner1, "#3");
        item[3] = new Item(R.drawable.banner2, "#4");
        item[4] = new Item(R.drawable.banner1, "#5");
        for (int i = 0; i < 5; i++) {
            items.add(item[i]);
        }
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
    }
}
