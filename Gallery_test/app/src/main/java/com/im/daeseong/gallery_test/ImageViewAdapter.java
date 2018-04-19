package com.im.daeseong.gallery_test;

import android.app.Activity;
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

    public ImageViewAdapter(Context context, int ResourceID, ArrayList<ImageItem> arrayList){
        super(context, ResourceID, arrayList);
        this.context = context;
        this.ResourceID = ResourceID;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;

        if(view == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(ResourceID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)view.findViewById(R.id.ivTitle);
            viewHolder.textView = (TextView)view.findViewById(R.id.tvTitle);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        ImageItem item = arrayList.get(position);
        viewHolder.imageView.setImageBitmap(item.getBitmap());
        viewHolder.textView.setText(item.getTitle());
        return view;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}


