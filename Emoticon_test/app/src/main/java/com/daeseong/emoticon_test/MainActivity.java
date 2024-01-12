package com.daeseong.emoticon_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private Button btn1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        btn1 = findViewById(R.id.btn1);
        tv1 = findViewById(R.id.tv1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = et1.getText().toString();
                tv1.setText(inputText);
            }
        });

        init();
    }

    private void init() {

        String value1 = "❤️💚♤☆♧¥♡♤☆♧🐵🐒🐕🦁🐕‍🐺🦎🦄🐴🐆🐅🐍🦌";
        String value2 = "♤☆♧¥♡♤☆♧";
        et1.setText(value1);
    }
}