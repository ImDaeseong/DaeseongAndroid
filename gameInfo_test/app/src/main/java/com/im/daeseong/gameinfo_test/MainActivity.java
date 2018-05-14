package com.im.daeseong.gameinfo_test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                List<GameInfo.GameItem> gameItems =  GameInfo.getGameApp(MainActivity.this);
                for (int i=0; i < gameItems.size(); i++){
                    //Log.e(TAG, "packageName:" + gameItems.get(i).packageName);
                    Toast.makeText(MainActivity.this, gameItems.get(i).packageName, Toast.LENGTH_SHORT).show();
                }
            }
        }, 100);
    }
}
