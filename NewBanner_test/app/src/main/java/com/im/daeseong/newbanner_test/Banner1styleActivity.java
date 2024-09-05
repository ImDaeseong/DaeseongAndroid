package com.im.daeseong.newbanner_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.im.daeseong.newbanner_test.Banner1_style.BannerView;

public class Banner1styleActivity extends AppCompatActivity {

    private static final String TAG = Banner1styleActivity.class.getSimpleName();

    private BannerView BannerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner1style);

        BannerView1 = (com.im.daeseong.newbanner_test.Banner1_style.BannerView)findViewById(R.id.BannerView1);

        Bitmap bitmap = null;
        try{
            bitmap = new BannerdisplayImage().execute("https://.png").get();
        }catch (Exception e){
            e.printStackTrace();
        }
        BannerView1.setImage(bitmap, R.drawable.number1);
    }
}
