package com.daeseong.ThreadTask_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.daeseong.ThreadTask_test.Util.DownloadManager;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private ImageView iv1;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        iv1 = findViewById(R.id.iv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isNetworkAvailable()){
                    return;
                }

                String surl = "https://";
                DownloadManager.getInstance().getImage(surl, new DownloadManager.onCompleteListen() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        //Log.e(TAG, "onCompleted:" + bitmap);
                        iv1.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(String sMessage) {
                        Log.e(TAG, "onError:" + sMessage);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}