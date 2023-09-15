package com.im.daeseong.mainui_test;

import com.google.android.material.navigation.NavigationView;//import android.support.design.widget.NavigationView;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;//import android.support.v7.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;//import android.support.v7.widget.Toolbar;
import androidx.core.view.GravityCompat;//import android.support.v4.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;//import android.support.v4.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;//import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

public class Mainui5Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui5);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
