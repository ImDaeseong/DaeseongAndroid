package com.daeseong.gridlayoutmanager_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Item> itemList;

    public RecyclerViewAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv1.setText(itemList.get(position).getTitle());
        holder.iv1.setBackgroundResource(itemList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv1;
        private ImageView iv1;

        public MyViewHolder(View v) {
            super(v);

            tv1 = (TextView)v.findViewById(R.id.tv1);
            iv1 = (ImageView)v.findViewById(R.id.iv1);
        }
    }

}


