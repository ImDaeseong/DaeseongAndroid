package com.im.daeseong.gallery_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class PopupViewAdapter extends BaseAdapter {

    private Context context;
    private List<ImageItem> arrayList;

    public PopupViewAdapter(Context context){
        this.context = context;
        this.arrayList = new ArrayList<ImageItem>();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item1, viewGroup, false);
        }

        ImageItem item = arrayList.get(i);

        ImageView imageView = (ImageView)view.findViewById(R.id.ivTitle);
        TextView textView = (TextView)view.findViewById(R.id.tvTitle);
        imageView.setImageBitmap(item.getBitmap());
        textView.setText(item.getTitle());

        return view;
    }

    public void addPhoto(ImageItem item){
        this.arrayList.add(item);
        this.notifyDataSetChanged();
    }

}

