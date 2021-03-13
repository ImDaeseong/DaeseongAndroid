package com.daeseong.countdownlabel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private TextViewEx tv1;
    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.tv1);
        tv1.setCount(10);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.stopTimer();
                tv1.startTimer();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.stopTimer();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        tv1.stopTimer();
    }
}
