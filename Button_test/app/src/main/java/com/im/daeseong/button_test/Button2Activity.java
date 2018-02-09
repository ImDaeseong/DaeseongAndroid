package com.im.daeseong.button_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Button2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button2);
    }

    public void button2_Click(View v){
        Toast.makeText(Button2Activity.this, "button2_Click", Toast.LENGTH_SHORT).show();
    }

}
