package com.daeseong.floatingview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        topFloating();
        bottomFloating();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, FloatinView1Activity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, FloatinView2Activity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, FloatinView3Activity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, FloatinView4Activity.class));
                break;
        }
    }

    private void bottomFloating() {
        try {
            ViewGroup rootView = findViewById(android.R.id.content);
            FloatingImgBottomLayout floatingImgBottomLayout = new FloatingImgBottomLayout(this);
            floatingImgBottomLayout.show(rootView);

            View floatingView = floatingImgBottomLayout.getFloatingView();

            floatingView.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    try {
                        startActivity(new Intent(MainActivity.this, FloatinView1Activity.class));
                        floatingImgBottomLayout.hide();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void topFloating() {
        ViewGroup rootView = findViewById(android.R.id.content);
        FloatingImgTopLayout floatingImgTopLayout = new FloatingImgTopLayout(this);
        floatingImgTopLayout.show(rootView, R.id.cL1);

        /*
        ViewGroup rootView = findViewById(android.R.id.content);
        FloatingDoubleImgTopLayout floatingDoubleImgTopLayout = new FloatingDoubleImgTopLayout(this);
        floatingDoubleImgTopLayout.show(rootView, R.id.cL1);
        */
    }

}