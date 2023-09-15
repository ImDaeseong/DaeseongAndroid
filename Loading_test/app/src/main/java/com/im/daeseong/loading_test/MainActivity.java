package com.im.daeseong.loading_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer = null;

    private Button btn1, btn2, btn3;

    private LoadingOverlay loadingOverlay;

    private LoadingDialog loadingDialog;

    private LoadingDialog1 loadingDialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingDialog = new LoadingDialog(MainActivity.this);

        loadingDialog1 = new LoadingDialog1(this);


        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
                showLoading();
            }
        });

        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!loadingDialog.isShowing()){
                    showLoading1();
                } else {
                    hideLoading1();
                }
            }
        });

        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLoading2();
            }
        });

    }

    void showLoading2()
    {
        loadingDialog1.show();
    }

    void hideLoading2()
    {
        try {
            loadingDialog1.dismiss();
        } catch (Exception e) {}
    }

    void showLoading1()
    {
        loadingDialog.show();
    }

    void hideLoading1()
    {
        try {
            loadingDialog.dismiss();
        } catch (Exception e) {}
    }

    void showLoading()
    {
        if (loadingOverlay == null) {
            loadingOverlay = new LoadingOverlay(this);
        }
        loadingOverlay.show();
    }

    void hideLoading()
    {
        if (loadingOverlay != null && loadingOverlay.isShowing()) {
            loadingOverlay.dismiss();
        }
    }

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

                    if (nTimeCount > 60) {

                        closeTimer();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoading();
                            }
                        });
                    }

                }catch (Exception e){
                }
            }

        }, 0, 1000);
    }

}
