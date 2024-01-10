package com.im.daeseong.dialog_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;

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
        //startActivity(new Intent(this, Style5Activity_Dialog.class));
        //startActivity(new Intent(this, Style6Activity_Dialog.class));
        //startActivity(new Intent(this, Style7Activity_Dialog.class));
        //startActivity(new Intent(this, Style8Activity_Dialog.class));
        startActivity(new Intent(this, Style9Activity_Dialog.class));
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
