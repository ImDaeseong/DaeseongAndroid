package com.im.daeseong.banner_test;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Banner2Activity extends AppCompatActivity {

    private static final String TAG = Banner2Activity.class.getSimpleName();

    private ImageDownLoader imageDownLoader;

    private ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);

        Toast.makeText(MyApplication.getInstance(), "start ImageDownLoader", Toast.LENGTH_SHORT).show();

        String url1 = "https://.png";
        String url2 = "https://.png";
        String url3 = "https://.png";
        String url4 = "https://.png";
        imageDownLoader = new ImageDownLoader(imageView1);
        imageDownLoader.execute(url1);

        imageDownLoader = new ImageDownLoader(imageView2);
        imageDownLoader.execute(url2);

        imageDownLoader = new ImageDownLoader(imageView3);
        imageDownLoader.execute(url3);

        imageDownLoader = new ImageDownLoader(imageView4);
        imageDownLoader.execute(url4);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(imageDownLoader.getStatus() != AsyncTask.Status.FINISHED){
            imageDownLoader.cancel(true);
        }

    }
}
