package com.daeseong.bottomnavigationview_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        initFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //Log.e(TAG, String.valueOf(menuItem.getItemId()));

                switch (menuItem.getItemId()){
                    case R.id.list:
                        SelectIndex(0);
                        break;
                    case R.id.sentence:
                        SelectIndex(1);
                        break;
                    case R.id.word:
                        SelectIndex(2);
                        break;
                    case R.id.myword:
                        SelectIndex(3);
                        break;
                    case R.id.setting:
                        SelectIndex(4);
                        break;
                }
                return true;
            }
        });

        SelectIndex(0);
    }

    private void initFragment(){
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
    }

    private void SelectIndex(int nIndex){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (nIndex){
            case 0:
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
                fragmentTransaction.replace(R.id.frameLayout, fragment1);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
                fragmentTransaction.replace(R.id.frameLayout, fragment2);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
                fragmentTransaction.replace(R.id.frameLayout, fragment3);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
                fragmentTransaction.replace(R.id.frameLayout, fragment4);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
                fragmentTransaction.replace(R.id.frameLayout, fragment5);
                fragmentTransaction.commit();
                break;
        }
    }
}
