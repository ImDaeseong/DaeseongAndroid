package com.im.daeseong.banner_test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Banner4Activity extends AppCompatActivity {

    private static final String TAG = Banner4Activity.class.getSimpleName();

    private Map<String, Bitmap> map = new HashMap<String, Bitmap>();

    private HashMap<String, Bitmap> map1 = new HashMap<String, Bitmap>();

    private ImageView imageView1, imageView2, imageView3, imageView4;
    private Button button1;

    private boolean bClick = false;

    String url1 = "https://.png";
    String url2 = "https://.png";
    String url3 = "https://.png";
    String url4 = "https://.png";
    String url5 = "https://.png";
    String url6 = "https://.png";
    String url7 = "https://.png";
    String url8 = "https://.png";
    String url9 = "https://.png";
    String url10 = "https://.png";
    String url11 = "https://.png";
    String url12 = "https://.png";
    String url13 = "https://.png";
    String url14 = "https://.png";
    String url15 = "https://.png";
    String url16 = "https://.png";
    String url17 = "https://.png";
    String url18 = "https://.png";
    String url19 = "https://.png";
    String url20 = "https://.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner4);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bClick){

                    bClick = false;
                    new Banner4Task().execute( new String[]{url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12, url13, url14, url15, url16, url17, url18, url19, url20});

                }else {
                    bClick = true;
                    Banner4Thread thread = new Banner4Thread(new String[]{url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12, url13, url14, url15, url16, url17, url18, url19, url20});
                    thread.start();
                }

            }
        });

        new Banner4Task().execute( new String[]{url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12, url13, url14, url15, url16, url17, url18, url19, url20});
    }


    private class Banner4Thread extends Thread{

        private  String[] params;
        public Banner4Thread(String[] params){
            this.params =params;
        }

        @Override
        public void run() {

            try{
                String sFileName = "";
                Bitmap bitmap = null;
                for(String url : params){
                    sFileName = String_util.getImageName(url);
                    bitmap = DownLoadImage(url);

                    //오류로 인해 이미지를 받아오지 못할 경우 가지고 있는 리소스를 이용한다.
                    if(bitmap == null){
                        if(sFileName.equals("logo1.png"))
                            bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number1);
                        else if(sFileName.equals("logo2.png"))
                            bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number2);
                        else if(sFileName.equals("logo3.png"))
                            bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number3);
                        else if(sFileName.equals("logo4.png"))
                            bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number4);
                    }
                    map1.put(sFileName, bitmap);
                }

                Log.e(TAG, "Thread bitmap Download complete");

                for( String key : map1.keySet() ){
                    if(key.equals("logo1.png")){

                        final Bitmap bBkey = map1.get(key);
                        imageView1.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView1.setImageBitmap(bBkey);
                            }
                        });
                    }else if(key.equals("logo2.png")) {

                        final Bitmap bBkey = map1.get(key);
                        imageView2.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView2.setImageBitmap(bBkey);
                            }
                        });
                    }else if(key.equals("logo3.png")) {

                        final Bitmap bBkey = map1.get(key);
                        imageView3.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView3.setImageBitmap(bBkey);
                            }
                        });
                    }else if(key.equals("logo4.png")) {

                        final Bitmap bBkey = map1.get(key);
                        imageView4.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView4.setImageBitmap(bBkey);
                            }
                        });
                    }
                }

                /*
                // 방법1
                Iterator<String> keys = map1.keySet().iterator();
                while( keys.hasNext() ){
                    String key = keys.next();
                    Log.e(TAG, "Thread Iterator:" + String.format("키 : %s, 값 : %s", key, map1.get(key)) );
                }

                // 방법2
                for( Map.Entry<String, Bitmap> elem : map1.entrySet() ){
                    Log.e(TAG, "Thread Entry:" + String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
                }

                // 방법3
                for( String key : map1.keySet() ){
                    Log.e(TAG, "Thread keySet:" + String.format("키 : %s, 값 : %s", key, map1.get(key)) );
                }
                */
                map1.clear();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class Banner4Task extends AsyncTask<String, Void, Boolean > {

        @Override
        protected Boolean doInBackground(String... params) {
            String sFileName = "";
            Bitmap bitmap = null;
            for(String url : params){
                sFileName = String_util.getImageName(url);
                bitmap = DownLoadImage(url);

                //오류로 인해 이미지를 받아오지 못할 경우 가지고 있는 리소스를 이용한다.
                if(bitmap == null){

                    if(sFileName.equals("logo1.png"))
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number1);
                    else if(sFileName.equals("logo2.png"))
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number2);
                    else if(sFileName.equals("logo3.png"))
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number3);
                    else if(sFileName.equals("logo4.png"))
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number4);
                }
                map.put(sFileName, bitmap);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean){

                for( String key : map.keySet() ){
                    if(key.equals("logo1.png")){
                        imageView1.setImageBitmap( map.get(key));
                    }else if(key.equals("logo2.png")) {
                        imageView2.setImageBitmap(map.get(key));
                    }else if(key.equals("logo3.png")) {
                        imageView3.setImageBitmap(map.get(key));
                    }else if(key.equals("logo4.png")) {
                        imageView4.setImageBitmap(map.get(key));
                    }
                }

                /*
                // 방법1
                Iterator<String> keys = map.keySet().iterator();
                while( keys.hasNext() ){
                    String key = keys.next();
                    Log.e(TAG, "Iterator:" + String.format("키 : %s, 값 : %s", key, map.get(key)) );
                }

                // 방법2
                for( Map.Entry<String, Bitmap> elem : map.entrySet() ){
                    Log.e(TAG, "Entry:" + String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
                }

                // 방법3
                for( String key : map.keySet() ){
                    Log.e(TAG, "keySet:" + String.format("키 : %s, 값 : %s", key, map.get(key)) );
                }
                */
                map.clear();
            }
        }

    }

    private Bitmap DownLoadImage(String sUrl) {

        String urlImage = sUrl;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Bitmap bitmap = null;
        try{
            URL url = new URL(urlImage);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if(resCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(httpURLConnection != null){
                try{
                    httpURLConnection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
