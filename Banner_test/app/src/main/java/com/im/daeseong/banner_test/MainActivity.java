package com.im.daeseong.banner_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
    private AsyncTask mtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);

        Log.e(TAG, "onCreate:new ProgressDialog(this)");
    }

    public void button1_Click(View v){

        String url1 = "https://.png";
        String url2 = "https://.png";
        String url3 = "https://.png";
        String url4 = "https://.png";
        String url5 = "https://.png";
        mtask = new DownloadImage().execute(url1, url2, url3, url4, url5);
    }

    private class DownloadImage extends AsyncTask<String, Integer, List<Bitmap> > {

        @Override
        protected List<Bitmap> doInBackground(String... params) {

            int urlCount = params.length;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            Bitmap bitmap = null;
            List<Bitmap> bitmaps = new ArrayList<>();

            for(int i=0; i<urlCount; i++){

                if(mtask.isCancelled()) break;

                try{
                    URL url = new URL(params[i]);
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
                    bitmaps.add(bitmap);

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
            }
            return bitmaps;
        }

        @Override
        protected void onPostExecute(List<Bitmap> bitmaps) {

            try {
                Log.e(TAG, "onPostExecute:progressDialog.dismiss()");

                for(int i=0; i <bitmaps.size(); i++){

                    Bitmap bitmap = bitmaps.get(i);
                    if(bitmap == null){
                        Log.e(TAG, "bitmap null:" + String.valueOf(i));
                    }

                    if(i == 0){
                        imageView1.setImageBitmap(bitmap);
                    }else if(i == 1){
                        imageView2.setImageBitmap(bitmap);
                    }else if(i == 2){
                        imageView3.setImageBitmap(bitmap);
                    }else if(i == 3){
                        imageView4.setImageBitmap(bitmap);
                    }else if(i == 4){
                        imageView5.setImageBitmap(bitmap);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute:progressDialog.show()");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e(TAG, "onProgressUpdate:" + String.valueOf(values[0]));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e(TAG, "AsyncTask onCancelled");
        }
    }

}
