package com.daeseong.swiperefreshlayout_test;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutEx extends SwipeRefreshLayout {

    private OnRefreshListener onRefreshListener;

    public SwipeRefreshLayoutEx(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SwipeRefreshLayoutEx(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){

        setColorSchemeResources(R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500);

        //스와이프 민감도 설정(기본:120)
        setDistanceToTriggerSync(20);

        setSize(SwipeRefreshLayout.DEFAULT);

        //스와이프 프로그래스바 위치 - 숨김
        //swipeRefreshLayout.setProgressViewOffset(true, -10000, -10000);
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {

        super.setOnRefreshListener(listener);
        this.onRefreshListener = listener;
    }

    public void hideProgressBar(boolean bProgressHide){

        if(bProgressHide) {

            //스와이프 프로그래스바 위치 - 숨김
            setProgressViewOffset(true, -10000, -10000);
        }
    }
}
