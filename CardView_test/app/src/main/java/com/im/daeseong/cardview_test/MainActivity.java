package com.im.daeseong.cardview_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2, button3, button4, button5, button6, button7;

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

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, CardView1Activity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, CardView2Activity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, CardView3Activity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, CardView4Activity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, CardView5Activity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, CardView6Activity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, CardView7Activity.class));
                break;
        }
    }
}
