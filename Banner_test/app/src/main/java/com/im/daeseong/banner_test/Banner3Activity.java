package com.im.daeseong.banner_test;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class Banner3Activity extends AppCompatActivity {

    private static final String TAG = Banner3Activity.class.getSimpleName();

    private ImageView imageView1, imageView2, imageView3, imageView4;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner3);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e(TAG, "getCompleteBannerItem1:" + Banner_util.getInstance().getCompleteBannerItem1());

                List<RowItem> rowItems = Banner_util.getInstance().getBanner1();
                for(int i=0; i<rowItems.size(); i++){
                    Bitmap bitmap = rowItems.get(i).getBitmap();
                    if(i == 0){
                        imageView1.setImageBitmap(bitmap);
                    }else if(i == 1){
                        imageView2.setImageBitmap(bitmap);
                    }else if(i == 2){
                        imageView3.setImageBitmap(bitmap);
                    }else if(i == 3){
                        imageView4.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }
}
