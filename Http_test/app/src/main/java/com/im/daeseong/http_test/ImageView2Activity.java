package com.im.daeseong.http_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.im.daeseong.http_test.HttpUtil.DownloadImage1;

public class ImageView2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view2);

        try {

            if (IsConnection()) {
                String url1 = "https://";
                DownloadImage1 downloadImage1 = new DownloadImage1(this);
                downloadImage1.execute(url1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ImageViewBitmap(Bitmap bitmap){
        if(bitmap != null){
            ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
            imageView1.setImageBitmap(bitmap);
        }else{
            ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
            imageView1.setImageResource(R.drawable.r);
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
