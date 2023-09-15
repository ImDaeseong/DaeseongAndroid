package com.im.daeseong.serviceboot_completed_test;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import android.util.Log;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameCheckService  extends Service {

    private static final String TAG = GameCheckService.class.getSimpleName();


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            Log.e(TAG, "handleMessage");

            LogToFile_util.write(msg.obj.toString());

            //Toast.makeText(getApplicationContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
            return true;
        }
    });

    private Timer timer = null;

    private void closeTimer(){
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }catch (Exception e){
        }
    }

    private void startTimer(){

        closeTimer();

        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                List<GameInfo.GameItem> gameItems =  GameInfo.getGameApp(GameCheckService.this);
                for (int i=0; i < gameItems.size(); i++){
                    //Log.e(TAG, "packageName:" + gameItems.get(i).packageName);
                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    msg.obj = gameItems.get(i).packageName;
                    handler.sendMessage(msg);//handler.sendEmptyMessage(0);
                }
            }
        }, 0, 5000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LogToFile_util.init(this);
        LogToFile_util.write("GameCheckService start");

        Log.e(TAG, "onCreate");

        startTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LogToFile_util.write("GameCheckService end");

        closeTimer();

        handler.removeMessages(0);

        Log.e(TAG, "onDestroy");
    }
}
