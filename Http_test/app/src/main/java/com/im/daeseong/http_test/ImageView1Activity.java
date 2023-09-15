package com.im.daeseong.http_test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.im.daeseong.http_test.HttpUtil.DownloadImage;

public class ImageView1Activity extends AppCompatActivity {

    private DownloadImage downloadImage;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view1);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);

        try {
            if (IsConnection()) {

                String url1 = "https://jpg";
                downloadImage = new DownloadImage(imageView1);
                downloadImage.execute(url1);

                String url2 = "https://png";
                downloadImage = new DownloadImage(imageView2);
                downloadImage.execute(url2);

                String url3 = "https://png";
                downloadImage = new DownloadImage(imageView3);
                downloadImage.execute(url3);

                String url4 = "https://png";
                downloadImage = new DownloadImage(imageView4);
                downloadImage.execute(url4);

                String url5 = "https://png";
                downloadImage = new DownloadImage(imageView5);
                downloadImage.execute(url5);

            } else {
                imageView1.setBackgroundResource(R.drawable.r);
                imageView2.setBackgroundResource(R.drawable.r);
                imageView3.setBackgroundResource(R.drawable.r);
                imageView4.setBackgroundResource(R.drawable.r);
                imageView5.setBackgroundResource(R.drawable.r);
            }
        }catch (Exception e){

            downloadImage.cancel(true);

            imageView1.setBackgroundResource(R.drawable.r);
            imageView2.setBackgroundResource(R.drawable.r);
            imageView3.setBackgroundResource(R.drawable.r);
            imageView4.setBackgroundResource(R.drawable.r);
            imageView5.setBackgroundResource(R.drawable.r);

            e.printStackTrace();
        }

    }

    public boolean IsConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return false;
        }

        if(!networkInfo.isConnected()){
            return  false;
        }

        if(!networkInfo.isAvailable()){
            return false;
        }
        return true;
    }

}
