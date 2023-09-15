package com.im.daeseong.swipe_test;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, Swipe1Activity.class));
                //startActivity(new Intent(MainActivity.this, Swipe2Activity.class));
                //startActivity(new Intent(MainActivity.this, Swipe3Activity.class));
                startActivity(new Intent(MainActivity.this, Swipe4Activity.class));
            }
        });

    }
}
