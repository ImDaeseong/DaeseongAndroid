package com.im.daeseong.drawerchild_test;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBarDrawerToggle;//import android.support.v7.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;//import android.support.constraint.ConstraintLayout;
import androidx.core.view.GravityCompat;//import android.support.v4.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;//import android.support.v4.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;//import android.support.design.widget.NavigationView;

public class BaseActivity extends AppCompatActivity {

    private DrawerLayout Main_drawerLayout;
    private NavigationView Main_navigationView;
    private ActionBarDrawerToggle toggle;
    private View headerView;

    private Button btnheader1, btnheader2;

    @Override
    public void setContentView(int layoutResID) {
        //super.setContentView(layoutResID);

        Main_drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        ConstraintLayout activityContainer = (ConstraintLayout) Main_drawerLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(Main_drawerLayout);

        Main_navigationView = (NavigationView)findViewById(R.id.mainnav_view);

        headerView = Main_navigationView.getHeaderView(0);

        toggle = new ActionBarDrawerToggle(this, Main_drawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        Main_drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        btnheader1 = (Button)headerView.findViewById(R.id.btnheader1);
        btnheader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseActivity.this, Child1Activity.class));
                closeSlideMenu();
            }
        });

        btnheader2 = (Button)headerView.findViewById(R.id.btnheader2);
        btnheader2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseActivity.this, Child2Activity.class));
                closeSlideMenu();
            }
        });
    }

    private void closeSlideMenu(){

        if (Main_drawerLayout.isDrawerOpen(GravityCompat.END)) {
            Main_drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            Main_drawerLayout.openDrawer(GravityCompat.END);
        }
    }

}
