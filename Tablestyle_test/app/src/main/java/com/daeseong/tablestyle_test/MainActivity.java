package com.daeseong.tablestyle_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

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

        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, Main1Activity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, Main2Activity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, Main3Activity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, Main4Activity.class));
                break;
        }
    }
}