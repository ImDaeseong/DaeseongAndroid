package com.im.daeseong.singleclick_test;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final Handler handler = new Handler();

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new OnSingleClickListener(500) {
            @Override
            public void onSingleClick(View view) {
                fn_func1();
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }

    public void fn_func1(){

        if(CheckDoublePressHandler.isDoubleClick()){
            //Log.e(TAG , "onCheckDoublePressed");
            return;
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }catch (Exception e){
                }
            }
        });

    }

}
