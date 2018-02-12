package com.im.daeseong.newbanner_test.Banner1_style;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.im.daeseong.newbanner_test.Banner1styleActivity;
import com.im.daeseong.newbanner_test.MainActivity;
import com.im.daeseong.newbanner_test.MyApplication;
import com.im.daeseong.newbanner_test.R;

/**
 * Created by Daeseong on 2018-02-09.
 */

public class BannerView extends RelativeLayout implements View.OnTouchListener  {

    private static final String TAG = BannerView.class.getSimpleName();

    private ImageView banner1style;
    private Context context;

    public BannerView(Context context){
        super(context);
        initView(context);
    }

    public BannerView(Context context, AttributeSet attributeSet){
        super(context, attributeSet, 0);
        initView(context);
    }

    public BannerView(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context, attributeSet, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        this.context =context;
        View view = LayoutInflater.from(context).inflate(R.layout.banner1_style_view, this);
        banner1style = (ImageView)view.findViewById(R.id.banner1style_widget);
        banner1style.setOnTouchListener(this);
    }

    public void setImage(Bitmap bitmap, int nResourceID){
        if(bitmap == null){
            banner1style.setImageResource(nResourceID);
        }else{
            banner1style.setImageBitmap(bitmap);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouch");

                try {
                    ((Banner1styleActivity) context).startActivity(new Intent(((Banner1styleActivity) context), MainActivity.class));
                    ((Banner1styleActivity) context).finish();
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case MotionEvent.ACTION_DOWN:
                break;
            default:
                break;
        }
        return true;
    }
}
