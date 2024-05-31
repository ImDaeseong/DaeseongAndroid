package com.daeseong.loading_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.daeseong.loading_test.Controls.LoadingDialog1;
import java.util.Timer;
import java.util.TimerTask;

public class Loading1Activity extends AppCompatActivity {

    private LoadingDialog1 loadingDialog;
    private Timer timer;

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading1);

        loadingDialog = new LoadingDialog1(this);

        startTimer();

        showLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
        closeTimer();
    }

    private void showLoading() {
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    private void hideLoading() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        }, 1000);
    }

    private void closeTimer() {
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startTimer() {
        closeTimer();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            int nTimeCount = 0;
            @Override
            public void run() {
                try {
                    nTimeCount++;
                    if (nTimeCount > 5) {
                        closeTimer();
                        runOnUiThread(() -> hideLoading());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
    }
}