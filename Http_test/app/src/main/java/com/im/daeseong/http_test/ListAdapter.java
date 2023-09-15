package com.im.daeseong.http_test;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.im.daeseong.http_test.HttpUtil.DownloadImage;
import com.im.daeseong.http_test.HttpUtil.DownloadJson;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.HolderList> {

    Context context;
    List<DownloadJson.Goods> data;

    public ListAdapter(List<DownloadJson.Goods> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public HolderList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new HolderList(view);
    }

    @Override
    public void onBindViewHolder(HolderList holder, int position) {
        DownloadJson.Goods goods = data.get(position);
        holder.textView1.setText(goods.getGoodsName()+ "");
        holder.textView2.setText(goods.getAdminID());

        try {
            DownloadImage downloadImage = new DownloadImage(holder.imageView1);
            downloadImage.execute(goods.getFileName1());
        }catch (Exception e){
            holder.imageView1.setBackgroundResource(R.drawable.r);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HolderList extends RecyclerView.ViewHolder{

        ImageView imageView1;
        TextView textView1;
        TextView textView2;

        public HolderList(View view){
            super(view);
            imageView1 = view.findViewById(R.id.imageView1);
            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
        }
    }
}
