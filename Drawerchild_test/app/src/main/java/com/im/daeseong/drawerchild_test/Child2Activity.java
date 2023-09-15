package com.im.daeseong.drawerchild_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Child2Activity extends BaseActivity {

    private TextView tv2;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child2);

        tv2 = (TextView)findViewById(R.id.tv2);
        tv2.setText("Child2Activity TextView");


        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click Child2Activity", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
