package com.daeseong.rxjava_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int nID =  v.getId();
        if (nID == R.id.button1) {
            startActivity(new Intent(this, Rxjava1Activity.class));
        } else if (nID == R.id.button2) {
            startActivity(new Intent(this, Rxjava2Activity.class));
        } else if (nID == R.id.button3) {
            startActivity(new Intent(this, Rxjava3Activity.class));
        } else if (nID == R.id.button4) {
            startActivity(new Intent(this, Rxjava4Activity.class));
        }

    }
}