package com.im.daeseong.button_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Button5Activity extends AppCompatActivity{

    Button button5, button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button5);

        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);

        button5.setOnClickListener(clickListener);
        button6.setOnClickListener(clickListener);
        button7.setOnClickListener(clickListener);
        button8.setOnClickListener(clickListener);
        button9.setOnClickListener(clickListener);
    }

    Button.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button5:
                    Toast.makeText(Button5Activity.this, "button5", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button6:
                    Toast.makeText(Button5Activity.this, "button6", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button7:
                    Toast.makeText(Button5Activity.this, "button7", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button8:
                    Toast.makeText(Button5Activity.this, "button8", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button9:
                    Toast.makeText(Button5Activity.this, "button9", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
