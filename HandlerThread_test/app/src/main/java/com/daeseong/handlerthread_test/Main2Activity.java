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

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1, button2;

    private Thread thread = null;
    public Handler handler = null;
    private myRunnable myRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

        myRunnable = new myRunnable();

        thread = new Thread(myRunnable);
        thread.start();
    }

    private void clear() {

        try {

            if(handler != null){
                handler.removeCallbacks(myRunnable);
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

            handler = null;
            thread = null;
        }
    }

    private class myRunnable implements java.lang.Runnable {

        @Override
        public void run() {

            Message message = Message.obtain();
            message.what = 0;
            message.obj = "myRunnable";
            handler.sendMessage(message);
        }
    }
}