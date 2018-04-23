package com.im.daeseong.gallery_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageViewAdapter  extends ArrayAdapter<ImageItem> {

    private Context context;
    private int ResourceID;
    private ArrayList<ImageItem> arrayList = new ArrayList<ImageItem>();
    private boolean bItem;

    public ImageViewAdapter(Context context, int ResourceID, ArrayList<ImageItem> arrayList, boolean bItem){
        super(context, ResourceID, arrayList);
        this.context = context;
        this.ResourceID = ResourceID;
        this.arrayList = arrayList;
        this.bItem = bItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            if(bItem){
                convertView = LayoutInflater.from(this.context).inflate(R.layout.grid_item, parent, false);
            }else {
                convertView = LayoutInflater.from(this.context).inflate(R.layout.grid_item1, parent, false);
            }
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivTitle);
        TextView textView = (TextView)convertView.findViewById(R.id.tvTitle);

        ImageItem item = arrayList.get(position);
        imageView.setImageBitmap(item.getBitmap());
        textView.setText(item.getTitle());

        return convertView;
    }

    public void addPhoto(ImageItem item){
        this.arrayList.add(item);
        this.notifyDataSetChanged();
    }

}


