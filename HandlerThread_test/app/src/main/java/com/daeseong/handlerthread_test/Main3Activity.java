package com.daeseong.handlerthread_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1, button2;

    private Thread thread = null;
    public Handler handler = null;
    private myRunnable1 myRunnable1 = null;
    private myRunnable2 myRunnable2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();

        tv1 = findViewById(R.id.tv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.sendEmptyMessage(1);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(handler != null){
                    Message message = Message.obtain();
                    message.what = 2;
                    message.obj = "sendMessage";
                    handler.sendMessage(message);
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

        handler = new Handler(getMainLooper()){

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                String sMsg = String.format("%d %s", (msg.what), (String) msg.obj);
                tv1.setText(sMsg);
            }
        };

        myRunnable1 = new myRunnable1();
        myRunnable2 = new myRunnable2();

        thread = new Thread(myRunnable1);
        thread.start();
    }

    private void clear() {

        try {

            if(handler != null){
                handler.removeCallbacks(myRunnable2);
                handler.removeCallbacks(myRunnable1);
            }

            if(handler != null){
                handler.removeMessages(0);
            }

            if (thread != null) {
                thread.interrupt();
            }

        } catch (Exception ex) {

            Log.e(TAG, ex.getMessage().toString());
        } finally {

            thread = null;
        }
    }

    private class myRunnable1 implements java.lang.Runnable {

        @Override
        public void run() {

            handler.post(myRunnable2);
        }
    }

    private class myRunnable2 implements java.lang.Runnable {

        @Override
        public void run() {

            Message message = Message.obtain();
            message.what = 0;
            message.obj = "myRunnable2";
            handler.sendMessage(message);
        }
    }
}