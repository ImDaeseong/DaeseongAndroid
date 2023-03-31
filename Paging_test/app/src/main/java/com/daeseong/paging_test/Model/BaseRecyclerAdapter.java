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
import java.util.ArrayList;
import java.util.List;
import com.daeseong.paging_test.R;

public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.MyViewHolder> {

    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    private ArrayList<SearchApi.itemData> list;

    private Context context;
    private View MainView;

    public BaseRecyclerAdapter(Context context){
        this.context = context;
        list  = new ArrayList<>();
    }

    public BaseRecyclerAdapter(Context context, ArrayList<SearchApi.itemData> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(MainView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {

            SearchApi.itemData item = list.get(position);
            holder.tv1.setText(item.ID);
            holder.tv2.setText(item.NAME);
            holder.tv3.setText(item.HTMLURL);

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public SearchApi.itemData getItem(int position) {
        return list.get(position);
    }

    public void remove(SearchApi.itemData item) {
        int position = list.indexOf(item);
        if (position > -1) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    private void add(SearchApi.itemData item) {

        list.add(item);
        notifyItemInserted(list.size());
    }

    public void addAll(List<SearchApi.itemData> list){

        for (SearchApi.itemData item : list) {
            add(item);
        }
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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.e(TAG, "MyViewHolder click");
                }
            });
        }
    }

}
