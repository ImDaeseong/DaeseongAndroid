package com.daeseong.bluetooth_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3, button4, button5, button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.button1) {
            startActivity(new Intent(this, MainActivity1.class));
        } else if (id == R.id.button2) {
            startActivity(new Intent(this, MainActivity2.class));
        } else if (id == R.id.button3) {
            startActivity(new Intent(this, MainActivity3.class));
        } else if (id == R.id.button4) {
            startActivity(new Intent(this, MainActivity4.class));
        } else if (id == R.id.button5) {
            startActivity(new Intent(this, MainActivity5.class));
        } else if (id == R.id.button6) {
            startActivity(new Intent(this, MainActivity6.class));
        }
    }

}