package com.daeseong.sendtest_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.et1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //다른앱에서 파라미터 전달
                String sParam = et1.getText().toString();
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.daeseong.receivetest_app", "com.daeseong.receivetest_app.MainActivity"));
                intent.putExtra("param", sParam);
                startActivity(intent);
            }
        });
    }
}