package com.im.daeseong.button_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Button1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button1);
    }

    public void button1_Click(View v){
        Toast.makeText(Button1Activity.this, "button1_Click", Toast.LENGTH_SHORT).show();
    }
}
