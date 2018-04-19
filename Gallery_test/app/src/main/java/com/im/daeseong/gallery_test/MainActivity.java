package com.im.daeseong.gallery_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView grView;
    private ImageViewAdapter imageViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grView = (GridView)findViewById(R.id.grView);
        imageViewAdapter = new ImageViewAdapter(this, R.layout.grid_item,  initImages());
        grView.setAdapter(imageViewAdapter);

        grView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageItem item = (ImageItem) adapterView.getItemAtPosition(i);
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<ImageItem> initImages(){

        try {

            final ArrayList<ImageItem> imageItems = new ArrayList<>();

            ArrayList<String> dirlist;
            ArrayList<String> imgs;

            dirlist = new ArrayList<>();
            if (SearchImage.getDirList(SearchImage.PICTURES) != null) {
                dirlist = SearchImage.getDirList(SearchImage.PICTURES);
            }
            dirlist.add(SearchImage.CAMERA);

            for (int i = 0; i < dirlist.size(); i++) {
                imgs = SearchImage.getImageList(dirlist.get(i));

                for (int k = 0; k < imgs.size(); k++) {
                    Bitmap bitmap = SearchImage.loadBitmap(imgs.get(k));
                    String title = SearchImage.getFileName(imgs.get(k));
                    imageItems.add(new ImageItem(bitmap, title));
                }
            }
            return imageItems;

        }catch (Exception e){
        }
        return null;
    }

}


