package com.daeseong.jsonobject_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    JSONObject jsonWrite = new JSONObject();
                    jsonWrite.put("index", 1);
                    jsonWrite.put("id", "com.netmarble.lineageII");
                    jsonWrite.put("title", "리니지2 레볼루션");
                    //Log.e(TAG, "jsonWrite:" + jsonWrite.toString());

                    if(jsonWrite.has("index")){
                        Log.e(TAG, "index:" + jsonWrite.getInt("index"));
                    }

                    if(jsonWrite.has("id")){
                        Log.e(TAG, "id:" + jsonWrite.getString("id"));
                    }

                    if(jsonWrite.has("title")){
                        Log.e(TAG, "title:" + jsonWrite.getString("title"));
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    //json array 생성
                    JSONObject jsonWrite = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    for(int i=0; i < 2; i++){
                        jsonArray.put( "title" + String.valueOf(i));
                    }
                    jsonWrite.put("ary", jsonArray);
                    //Log.e(TAG, "ary:" + jsonWrite.toString());

                    //json array 조회
                    JSONObject arykey = new JSONObject(jsonWrite.toString());
                    if(arykey.has("ary")){
                        JSONArray ary = arykey.getJSONArray("ary");
                        for(int i=0; i < ary.length(); i++){
                            Log.e(TAG, "ary value:" + ary.get(i));
                        }
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    //json array 생성
                    JSONObject jsonWrite = new JSONObject();
                    JSONArray jsonArray = new JSONArray();

                    for(int i=0; i < 2; i++){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("index", i+1);
                        jsonObject.put("id", "com.netmarble.lineageII");
                        jsonObject.put("title", "리니지2 레볼루션");
                        jsonArray.put(jsonObject);
                    }
                    jsonWrite.put("ary", jsonArray);
                    //Log.e(TAG, "ary:" + jsonWrite.toString());


                    //json array 조회
                    JSONObject arykey = new JSONObject(jsonWrite.toString());
                    if(arykey.has("ary")){
                        JSONArray ary = arykey.getJSONArray("ary");
                        for(int i=0; i < ary.length(); i++){
                            //Log.e(TAG, "ary value:" + ary.getJSONObject(i));
                            JSONObject jsonObject = ary.getJSONObject(i);
                            String index = jsonObject.getString("index");
                            String id = jsonObject.getString("id");
                            String title = jsonObject.getString("title");
                            Log.e(TAG, "index:" + index + " id:" + id + " title:" + title);
                        }
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

    }
}