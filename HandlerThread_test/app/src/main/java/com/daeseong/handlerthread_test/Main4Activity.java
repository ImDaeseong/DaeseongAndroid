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

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1, button2;

    private MyThread myThread = null;
    public Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        init();

        tv1 = findViewById(R.id.tv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(handler != null){
                    handler.sendEmptyMessage(1);
                }
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(handler != null && myThread != null){
                    myThread.sendMessage();
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

        myThread = new MyThread(handler, true,"sParam");
        myThread.start();
    }

    private void clear() {

        try {

            if(handler != null){
                handler.removeMessages(0);
            }

            if (myThread != null) {
                myThread.clear();
                myThread.interrupt();
            }

        } catch (Exception ex) {

            Log.e(TAG, ex.getMessage().toString());
        } finally {

            handler = null;
            myThread = null;
        }
    }

    private class MyThread extends Thread {

        private Handler handler = null;
        private String sParam;
        private boolean bRun = false;

        public MyThread(Handler handler, boolean bRun, String sParam){

            this.handler = handler;
            this.bRun = bRun;
            this.sParam = sParam;
        }

        @Override
        public void run() {
            super.run();

            while (bRun){

                //Log.e(TAG, "MyThread run");

                try{

                    if(handler != null){
                        handler.sendEmptyMessage(0);
                    }

                    //1초에 한번씩 전달
                    Thread.sleep(1000);

                }catch (Exception ex){
                }
            }
        }

        public void sendMessage(){

            Message message = Message.obtain();
            message.what = 2;
            message.obj = "sendMessage";
            handler.sendMessage(message);
        }

        public void clear(){
            bRun = false;
        }
    }
}