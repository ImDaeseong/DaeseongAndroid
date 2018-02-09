package com.im.daeseong.checkbox_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Check2Activity extends AppCompatActivity {

    private CheckBox checkBox1, checkBox2, checkBox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check2);

        checkBox1 =(CheckBox)findViewById(R.id.checkBox2);
        checkBox2 =(CheckBox)findViewById(R.id.checkBox3);
        checkBox3=(CheckBox)findViewById(R.id.checkBox4);

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ((CheckBox)v).isChecked() ){
                    Toast.makeText(Check2Activity.this, "checked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ((CheckBox)v).isChecked() ){
                    Toast.makeText(Check2Activity.this, "checked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ((CheckBox)v).isChecked() ){
                    Toast.makeText(Check2Activity.this, "checked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
