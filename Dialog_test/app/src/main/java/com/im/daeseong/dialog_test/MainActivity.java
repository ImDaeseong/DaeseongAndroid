package com.im.daeseong.dialog_test;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppCompatDialog loadingDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //startActivity(new Intent(this, Style1Activity_Dialog.class));
        //startActivity(new Intent(this, Style2Activity_Dialog.class));
        //startActivity(new Intent(this, Style3Activity_Dialog.class));
        //startActivity(new Intent(this, Style4Activity_Dialog.class));

    }

    @Override
    public void onBackPressed() {


        new Custom1_Dialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).show();
    }

}
