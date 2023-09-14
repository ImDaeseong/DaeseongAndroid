package com.im.daeseong.cardview_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;//import android.support.v7.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;//import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardView4Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view4);

        itemList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(itemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        prepareItems();
    }

    private void prepareItems(){

        Item item = new Item(R.drawable.banner1, "item1");
        itemList.add(item);

        item = new Item( R.drawable.banner2, "item2");
        itemList.add(item);

        item = new Item( R.drawable.banner1, "item3");
        itemList.add(item);

        item = new Item(R.drawable.banner2, "item4");
        itemList.add(item);

        recyclerViewAdapter.notifyDataSetChanged();
    }

}
