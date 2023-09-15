package com.im.daeseong.drawerchild_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private Button btnchild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnchild = (Button)findViewById(R.id.btnchild);
        btnchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click MainActivity", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
