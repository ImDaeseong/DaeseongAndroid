package com.im.daeseong.timer_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private Button btn1;

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

            int nTimeCount = 0;

            @Override
            public void run() {

                try {
                    nTimeCount++;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String sMsg = String.format("%d", nTimeCount);
                            tv1.setText(sMsg);
                        }
                    });

                    if (nTimeCount > 180) {  //3분까지만 체크

                        closeTimer();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Toast test", Toast.LENGTH_SHORT).show();
                                tv1.setText("완료");
                            }
                        });
                    }

                }catch (Exception e){
                }
            }

        }, 0, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });

        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        closeTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        closeTimer();
    }

}
