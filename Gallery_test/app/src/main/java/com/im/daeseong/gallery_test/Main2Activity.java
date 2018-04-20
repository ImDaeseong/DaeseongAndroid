package com.im.daeseong.gallery_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private GridView grView;
    private ImageViewAdapter imageViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        grView = (GridView)findViewById(R.id.grView);
        imageViewAdapter = new ImageViewAdapter(this, R.layout.grid_item1,  initImages(), false);
        grView.setAdapter(imageViewAdapter);

        grView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageItem item = (ImageItem) adapterView.getItemAtPosition(i);
                Toast.makeText(Main2Activity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<ImageItem> initImages(){
        try {
            ArrayList<ImageItem> imageItems = new ArrayList<>();
            Bitmap bitmap;
            String title;

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
            title = "title1";
            imageItems.add(new ImageItem(bitmap, title));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);
            title = "title2";
            imageItems.add(new ImageItem(bitmap, title));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg3);
            title = "title3";
            imageItems.add(new ImageItem(bitmap, title));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg4);
            title = "title4";
            imageItems.add(new ImageItem(bitmap, title));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg5);
            title = "title5";
            imageItems.add(new ImageItem(bitmap, title));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg6);
            title = "title6";
            imageItems.add(new ImageItem(bitmap, title));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg7);
            title = "title7";
            imageItems.add(new ImageItem(bitmap, title));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg8);
            title = "title8";
            imageItems.add(new ImageItem(bitmap, title));

            return imageItems;
        }catch (Exception e){
        }
        return null;
    }

}
