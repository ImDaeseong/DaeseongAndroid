package com.im.daeseong.cardview_test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;//import android.support.v4.content.ContextCompat;
import androidx.cardview.widget.CardView;//import android.support.v7.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<com.im.daeseong.cardview_test.Item> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Item> items, int item_layout){
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Item item = items.get(position);
        Drawable drawable = ContextCompat.getDrawable(context, item.getImage());

        /*
        if (SDK_VERSION >= 16){
            holder.image.setBackground(drawable);
        }else {
            holder.image.setBackgroundDrawable(drawable);
        }
        */
        holder.image.setBackgroundDrawable(drawable);


        holder.title.setText(item.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardView;

        public ViewHolder(View view){
            super(view);
            image = (ImageView)view.findViewById(R.id.image);
            title = (TextView)view.findViewById(R.id.title);
            cardView =(CardView)view.findViewById(R.id.cardview);
        }
    }

}