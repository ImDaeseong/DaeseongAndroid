package com.im.daeseong.drawerchild_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Child1Activity extends BaseActivity {

    private TextView tv1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child1);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText("Child1Activity TextView");


        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click Child1Activity", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
