package com.daeseong.onestore_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sUrl = "https://";
                DownloadJson downloadJson = new DownloadJson();
                downloadJson.setJsonListener(new DownloadJson.JsonListener() {
                    @Override
                    public void onResult(String sResult) {

                        if(TextUtils.isEmpty(sResult)){
                            Log.e(TAG, "sResult: error");
                        } else {
                            Log.e(TAG, "sResult: " + sResult);
                        }
                    }
                });
                downloadJson.execute(sUrl);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //앱 설치 및 리뷰
                String sOneProduct = "onestore://common/product/";
                String sPackageId = "0000207200";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sOneProduct + sPackageId));

                try {
                    startActivity(intent);
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "원스토어가 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String sWebUrl = "https://m.onestore.co.kr/mobilepoc/apps/appsDetail.omp?prodId=";
                    String sPackageId = "0000207200";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sWebUrl + sPackageId));
                    startActivity(intent);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //앱버전
                String sPackageId = "com.kakao.talk";
                OneStoreJson oneStoreJson = new OneStoreJson();
                oneStoreJson.setJsonListener(new OneStoreJson.JsonListener() {
                    @Override
                    public void onResult(String sResult) {

                        if(TextUtils.isEmpty(sResult)){

                            Log.e(TAG, "sResult: error");
                        } else {

                            //Log.e(TAG, "sResult: " + sResult);

                            try {

                                JSONObject resultObj = new JSONObject(sResult);
                                String sprodId = resultObj.get("prodId").toString();
                                if (TextUtils.isEmpty(sprodId)) {
                                    sprodId = "";
                                }

                                String sverNm = resultObj.get("verNm").toString();
                                if( TextUtils.isEmpty( sverNm ) ) {
                                    sverNm = "";
                                }

                                String sverCd = resultObj.get("verCd").toString();
                                if( TextUtils.isEmpty( sverCd ) ) {
                                    sverCd = "";
                                }

                                String result = resultObj.get("result").toString();
                                if( TextUtils.isEmpty( result ) ) {
                                    result = "";
                                }

                                String stitle = resultObj.get("title").toString();
                                if( TextUtils.isEmpty( stitle ) ) {
                                    stitle = "";
                                }

                                String ssellerNm = resultObj.get("sellerNm").toString();
                                if( TextUtils.isEmpty( ssellerNm ) ) {
                                    ssellerNm = "";
                                }

                                Log.e(TAG, "앱 ID :" + sprodId);
                                Log.e(TAG, "앱 Version :" + sverNm);
                                Log.e(TAG, "sverCd :" + sverCd);
                                Log.e(TAG, "result :" + result);
                                Log.e(TAG, "stitle :" + stitle);
                                Log.e(TAG, "ssellerNm :" + ssellerNm);

                                JSONObject jsonObject = new JSONObject(result);
                                if(jsonObject.getString("code").equals("000") && jsonObject.getString("desc").equals("success")){
                                    Log.e(TAG, "success");
                                }

                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                oneStoreJson.execute(sPackageId);
            }
        });
    }

}