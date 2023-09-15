package com.daeseong.okhttpclient_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.daeseong.okhttpclient_test.common.HttpUtilOK;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getlogin(this,"https://nid.naver.com/nidlogin.login", "", "");
        getBTC(this,"https://api.bithumb.com/public/ticker/BTC");
   }


    private void getBTC(Context context, String address) {

        HttpUtilOK.getBTC(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "getBTC onFailure");
                        //Toast.makeText(MainActivity.this,"getverson onFailure", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String res = response.body().string();
                    Log.e(TAG, res);

                    JSONObject resultObj = new JSONObject(res);
                    String status = resultObj.get("status").toString();
                    if( TextUtils.isEmpty( status ) ) {
                        status = "";
                    }

                    String data = resultObj.get("data").toString();
                    if( TextUtils.isEmpty( data ) ) {
                        data = "";
                    }

                    if(status.equals("0000")){

                        JSONObject resultData = new JSONObject(data);
                        String opening_price = resultData.get("opening_price").toString();
                        if( TextUtils.isEmpty( opening_price ) ) {
                            opening_price = "";
                        }
                        //Log.e(TAG, opening_price);

                        String closing_price = resultData.get("closing_price").toString();
                        if( TextUtils.isEmpty( closing_price ) ) {
                            closing_price = "";
                        }
                        //Log.e(TAG, closing_price);

                        String min_price = resultData.get("min_price").toString();
                        if( TextUtils.isEmpty( min_price ) ) {
                            min_price = "";
                        }
                        //Log.e(TAG, min_price);

                        String max_price = resultData.get("max_price").toString();
                        if( TextUtils.isEmpty( max_price ) ) {
                            max_price = "";
                        }
                        //Log.e(TAG, max_price);

                    } else {

                        Log.e(TAG, "서버 데이터 가져오기 에러");
                    }

                }catch (Exception ex){
                }
            }
        });
    }

    private void getlogin(Context context, String address, String ID, String PWD) {

        HttpUtilOK.getlogin(address, ID, PWD, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "getlogin onFailure");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String body = response.body().string();
                    Log.e(TAG, body);

                }catch (Exception ex){
                }
            }

        });

    }

}
