package com.daeseong.paging_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daeseong.paging_test.Common.DateTime;
import com.daeseong.paging_test.Common.HttpUtilOK;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1;

    private String sResult = "";

    private String sUrl = "";
    private String sSearchkey = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = (TextView) findViewById(R.id.tv1);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    sUrl = String.format("%s&q=%s:created:>%s", ConstantsUrl.sUrl1, sSearchkey, DateTime.getOneDayago());
                    getData(Main2Activity.this, sUrl);

                }catch (Exception ex){
                    Log.e(TAG, ex.getMessage().toString());
                }
            }
        });
    }

    private void getData(Context context, String sUrl) {

        HttpUtilOK.getData(sUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e(TAG, "getData onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) {

                try {

                    sResult = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tv1.setText("");
                            tv1.setText(sResult);
                        }
                    });

                }catch (Exception ex){
                    Log.e(TAG, ex.getMessage().toString());
                }
            }
        });
    }

}