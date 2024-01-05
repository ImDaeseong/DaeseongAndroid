package com.daeseong.fragmentcontainerview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.ui.NavigationUI;

public class Main4Activity extends AppCompatActivity {

    private NavController ncl;
    private BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ncl = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fcv)).getNavController();
        bnv = findViewById(R.id.bnv);
        NavigationUI.setupWithNavController(bnv, ncl);
    }
}