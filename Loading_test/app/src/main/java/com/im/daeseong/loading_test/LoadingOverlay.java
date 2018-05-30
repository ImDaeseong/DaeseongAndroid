package com.im.daeseong.loading_test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

public class LoadingOverlay extends Dialog {

    public LoadingOverlay(Context context){
        super(context, R.style.TransparentOverlay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.overlay_loading);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
