package com.im.daeseong.button_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Button6Activity extends AppCompatActivity implements View.OnClickListener{

    Button button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button6);

        button10 = (Button)findViewById(R.id.button10);
        button10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button10:
                Toast.makeText(Button6Activity.this, "button10", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
