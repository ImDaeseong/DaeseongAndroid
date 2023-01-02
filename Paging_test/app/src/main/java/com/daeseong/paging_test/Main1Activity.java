package com.daeseong.paging_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.daeseong.paging_test.Common.DateTime;
import com.daeseong.paging_test.Common.GetStringTask;
import com.daeseong.paging_test.Common.HttpUtil;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1;
    private ImageView iv1;
    private String sResult = "";

    private String sUrl = "";
    private String sSearchkey = "android";

    private HandlerThread thread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        downloadImg(ConstantsUrl.sImgUrl);

        initThread();

        iv1 = (ImageView)findViewById(R.id.iv1);
        tv1 = (TextView) findViewById(R.id.tv1);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText("");
                handler.sendEmptyMessage(1);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        StopThread();
    }

    private void initThread(){

        try {

            thread = new HandlerThread("paging");
            thread.start();

            handler = new Handler(thread.getLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    switch ((msg.what)){
                        case 1:
                            getData();
                            break;
                        default:
                            break;
                    }
                }
            };

        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    private void StopThread(){

        try {

            if(thread != null){
                thread.getLooper().quit();
                thread.quit();
            }

        }catch (Exception ex){
            ex.getMessage().toString();
        }
        finally {
            handler = null;
            thread = null;
        }
    }

    private void downloadImg(String sUrl){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try{

                    Bitmap bitmap = HttpUtil.GetDataBitmap(sUrl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            iv1.setImageBitmap(bitmap);
                        }
                    });
                }catch (Exception ex){
                    Log.e(TAG, ex.getMessage().toString());
                }
            }
        }).start();
    }

    private void getData(){

        try {

            sUrl = String.format("%s&q=%s:created:>%s", ConstantsUrl.sUrl1, sSearchkey, DateTime.getOneDayago());
            sResult = new GetStringTask().execute(sUrl);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    tv1.setText(sResult);
                }
            });

        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }
}