package com.im.daeseong.mainui_test;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ui2_4Fragment extends Fragment {

    public ui2_4Fragment() {
    }

    public static ui2_4Fragment newInstance() {
        ui2_4Fragment fragment = new ui2_4Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ui2_4, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
