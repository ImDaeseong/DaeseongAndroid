package com.daeseong.paging_test.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.daeseong.paging_test.API.SearchApi;
import com.daeseong.paging_test.R;
import java.util.ArrayList;
import java.util.List;

public class Recycler5Adapter extends RecyclerView.Adapter<Recycler5Adapter.MyViewHolder> {

    private static final String TAG = Recycler5Adapter.class.getSimpleName();

    private List<SearchApi.itemData> list  = new ArrayList<>();

    private Context context;

    public Recycler5Adapter(Context context){
        this.context = context;
    }

    public Recycler5Adapter(Context context, List<SearchApi.itemData> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SearchApi.itemData item = list.get(position);

        holder.tv1.setText(item.ID);
        holder.tv2.setText(item.NAME);
        holder.tv3.setText(item.HTMLURL);

        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, holder.tv1.getText().toString());
            }
        });

        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, holder.tv2.getText().toString());
            }
        });

        holder.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, holder.tv3.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {

        if(list == null){
            return 0;
        }
        return list.size();
    }

    public void addAll(List<SearchApi.itemData> list){

        if(this.list == null){
            this.list  = new ArrayList<>();
        }

        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void add(List<SearchApi.itemData> list){

        if(this.list == null){
            this.list  = new ArrayList<>();
        }

        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv1;
        public final TextView tv2;
        public final TextView tv3;

        public MyViewHolder(View view){
            super(view);

            tv1 = (TextView)view.findViewById(R.id.tv1);
            tv2 = (TextView)view.findViewById(R.id.tv2);
            tv3 = (TextView)view.findViewById(R.id.tv3);
        }
    }
}
