package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.daeseong.listview_test.Controls.LeftView;
import com.daeseong.listview_test.Controls.RightView;
import java.util.List;

public class ListView7Activity extends AppCompatActivity {

    private static final String TAG = ListView7Activity.class.getSimpleName();

    private ScrollView sv1, sv2;
    private LinearLayout ly1, ly2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view7);

        init();

        initDisplay();
    }

    private void init(){

        sv1 = (ScrollView)findViewById(R.id.sv1);
        ly1 = (LinearLayout)findViewById(R.id.ly1);
        ly1.setOrientation(LinearLayout.VERTICAL);
        ly1.setBackgroundColor(Color.GRAY);

        sv2 = (ScrollView)findViewById(R.id.sv2);
        ly2 = (LinearLayout)findViewById(R.id.ly2);
        ly2.setOrientation(LinearLayout.VERTICAL);
        ly2.setBackgroundColor(Color.WHITE);
    }

    private void initDisplay(){

        for(int i=0; i < MapApi.getInstance().getList().size(); i++){
            LeftView leftView = new LeftView(this, MapApi.getInstance().getList().get(i));
            leftView.setTag(i);
            leftView.setOnClickListener(leftviewClick);

            if(i ==0){
                leftView.setSelectColor();
            }
            ly1.addView(leftView);
        }
        updateDisplay(0);
    }

    private void updateDisplay(int nSelect){

        ly2.removeAllViews();

        String sKey = MapApi.getInstance().getList().get(nSelect);
        List<itemData> list =  MapApi.getInstance().getItem(sKey);
        if(list != null) {
            for (int i =0; i < list.size(); i++) {
                RightView rightView = new RightView(this, list.get(i).getLocName());
                rightView.setTag(i);
                rightView.setOnClickListener(rightviewClick);
                ly2.addView(rightView);
            }
        }
    }

    View.OnClickListener leftviewClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int nIndex = (int)view.getTag();
            for(int i=0; i < ly1.getChildCount(); i++){

                View v = ly1.getChildAt(i);
                if(i == nIndex){

                    v.setBackgroundColor(Color.WHITE);
                    updateDisplay(nIndex);

                }else{

                    v.setBackgroundColor(Color.GRAY);
                }
            }
        }
    };

    View.OnClickListener rightviewClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int nIndex = (int)view.getTag();
            for(int i=0; i < ly2.getChildCount(); i++){

                View v1 =ly2.getChildAt(i);
                if(nIndex == i){
                    if (v1 instanceof RightView) {
                        RightView rightView = (RightView) v1;
                        rightView.setClickColor();
                    }
                }else {
                    if (v1 instanceof RightView) {
                        RightView rightView = (RightView) v1;
                        rightView.setNormalColor();
                    }
                }
            }
        }
    };

}