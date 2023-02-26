package com.daeseong.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;

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
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        button10 = (Button)findViewById(R.id.button10);
        button11 = (Button)findViewById(R.id.button11);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, ListView1Activity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, ListView2Activity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, ListView3Activity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, ListView4Activity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, ListView5Activity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, ListView6Activity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, ListView7Activity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(this, ListView8Activity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(this, ListView9Activity.class));
                break;
            case R.id.button10:
                startActivity(new Intent(this, ListView10Activity.class));
                break;
            case R.id.button11:
                startActivity(new Intent(this, ListView11Activity.class));
                break;
        }
    }
}