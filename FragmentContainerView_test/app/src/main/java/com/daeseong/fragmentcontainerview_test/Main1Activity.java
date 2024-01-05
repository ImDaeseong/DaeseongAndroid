package com.daeseong.fragmentcontainerview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class Main1Activity extends AppCompatActivity {

    private FragmentContainerView fcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        fcv = findViewById(R.id.fcv);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fcv, new Fragment1())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}