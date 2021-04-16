package com.daeseong.ThreadTask_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daeseong.ThreadTask_test.Util.DownloadText;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private Handler handler = null;

    private TextView tv1;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        handler = new Handler(Looper.getMainLooper());

        tv1 = findViewById(R.id.tv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isNetworkAvailable()){
                    return;
                }

                String surl = "https://";
                DownloadText downloadText = new DownloadText(surl, handler);
                downloadText.setonDownloadTextListen(new DownloadText.onDownloadTextListen() {
                    @Override
                    public void onCompleted(DownloadText downloadText) {
                        //Log.e(TAG, "onCompleted:" + downloadText.getText());
                        tv1.setText(downloadText.getText());
                    }

                    @Override
                    public void onError(DownloadText downloadText, String sMessage) {
                        Log.e(TAG, "onError:" + sMessage);
                    }
                });
                downloadText.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

