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
import android.widget.ImageView;
import com.daeseong.ThreadTask_test.Util.DownloadRunnabe;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private Handler handler = null;

    private ImageView iv1;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        handler = new Handler(Looper.getMainLooper());

        iv1 = findViewById(R.id.iv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isNetworkAvailable()){
                    return;
                }

                String surl = "https://";
                DownloadRunnabe downloadRunnabe = new DownloadRunnabe(surl);
                downloadRunnabe.start(handler, new DownloadRunnabe.onDownloadRunnabeListen() {
                    @Override
                    public void onCompleted(DownloadRunnabe downloadRunnabe) {
                        //Log.e(TAG, "onCompleted:" + downloadRunnabe.getBitmap());
                        iv1.setImageBitmap(downloadRunnabe.getBitmap());
                    }

                    @Override
                    public void onError(DownloadRunnabe downloadRunnabe, String sMessage) {
                        Log.e(TAG, "onError:" + sMessage);
                    }
                });

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