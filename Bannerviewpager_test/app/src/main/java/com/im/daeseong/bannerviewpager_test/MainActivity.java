package com.im.daeseong.bannerviewpager_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, button7;

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

        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        button4.setOnClickListener(clickListener);
        button5.setOnClickListener(clickListener);
        button6.setOnClickListener(clickListener);
        button7.setOnClickListener(clickListener);
    }

    Button.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button1:
                    startActivity(new Intent(MainActivity.this, BannerA_Activity.class));
                    break;
                case R.id.button2:
                    startActivity(new Intent(MainActivity.this, BannerB_Activity.class));
                    break;
                case R.id.button3:
                    startActivity(new Intent(MainActivity.this, BannerC_Activity.class));
                    break;
                case R.id.button4:
                    startActivity(new Intent(MainActivity.this, BannerD_Activity.class));
                    break;
                case R.id.button5:
                    startActivity(new Intent(MainActivity.this, BannerE_Activity.class));
                    break;
                case R.id.button6:
                    startActivity(new Intent(MainActivity.this, BannerF_Activity.class));
                    break;
               case R.id.button7:
                   startActivity(new Intent(MainActivity.this, BannerG_Activity.class));
                    break;
            }
        }
    };

}
