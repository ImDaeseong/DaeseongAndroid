package com.daeseong.popupgrip_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent1 = new Intent(this, Popup1Activity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_bottom,0);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, Popup2Activity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_bottom,0);
                break;
        }
    }
}