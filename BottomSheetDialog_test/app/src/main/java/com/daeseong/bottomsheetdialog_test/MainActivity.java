package com.daeseong.bottomsheetdialog_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

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

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
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
            case R.id.button5:
                startActivity(new Intent(this, Main5Activity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, Main6Activity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, Main7Activity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(this, Main8Activity.class));
                overridePendingTransition(R.anim.slide_in_bottom,0);
                break;
            case R.id.button9:
                startActivity(new Intent(this, Main9Activity.class));
                break;
        }
    }
}