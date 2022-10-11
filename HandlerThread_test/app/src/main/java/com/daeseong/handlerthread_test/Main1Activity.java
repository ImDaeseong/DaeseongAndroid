package com.daeseong.handlerthread_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1, button2, button3;

    private HandlerThread handlerThread;
    public Handler handler = null;
    private myRunnable myRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        init();

        tv1 = findViewById(R.id.tv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(handlerThread != null){
                    handler.sendEmptyMessage(1);
                }
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(handlerThread != null){
                    Message message = Message.obtain();
                    message.what = 2;
                    message.obj = "sendMessage";
                    handler.sendMessage(message);
                }
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(handlerThread != null){

                    handler.post(myRunnable);
                    //handler.postDelayed(myRunnable, 1000);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        clear();
    }

    private void init(){

        handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper()){

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                String sMsg = String.format("%d %s", (msg.what), (String) msg.obj);
                tv1.setText(sMsg);
            }
        };

        myRunnable = new myRunnable();
    }

    private void clear(){

        try{

            if(handler != null){
                handler.removeCallbacks(myRunnable);
            }

            if(handler != null){
                handler.removeMessages(0);
            }

            if(handlerThread != null){
                handlerThread.getLooper().quit();
                handlerThread.quit();
            }

        }catch (Exception ex){

            Log.e(TAG, ex.getMessage().toString());
        }finally {

            handler = null;
            handlerThread = null;
        }
    }

    private class myRunnable implements java.lang.Runnable {

        @Override
        public void run() {

            if(handlerThread != null) {
                Message message = Message.obtain();
                message.what = 0;
                message.obj = "myRunnable";
                handler.sendMessage(message);
            }
        }
    }
}