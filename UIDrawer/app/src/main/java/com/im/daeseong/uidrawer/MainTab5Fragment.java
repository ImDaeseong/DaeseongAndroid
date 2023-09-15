package com.im.daeseong.uidrawer;

import android.os.Bundle;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Daeseong on 2018-03-10.
 */
public class MainTab5Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment5_main, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

