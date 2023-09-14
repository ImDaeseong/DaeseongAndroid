package com.im.daeseong.button_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Button3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button3);
    }

    public void button3_Click(View v){
        Toast.makeText(Button3Activity.this, "button3_Click", Toast.LENGTH_SHORT).show();
    }
}
