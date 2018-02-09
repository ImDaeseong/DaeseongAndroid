package com.im.daeseong.http_test;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.im.daeseong.http_test.HttpUtil.DownloadImage;
import com.im.daeseong.http_test.HttpUtil.DownloadImage2;
import com.im.daeseong.http_test.HttpUtil.DownloadJson;
import com.im.daeseong.http_test.HttpUtil.DownloadJson1;

import java.util.Arrays;
import java.util.List;

public class ImageTextView2Activity extends AppCompatActivity {

    private Button button1, button2;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text_view2);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        imageView1 = (ImageView)findViewById(R.id.imageView1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsConnection()) {
                    new DownloadImage2(ImageTextView2Activity.this).execute();
                }else {
                    Toast.makeText(getApplicationContext(), "Not Connect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsConnection()) {
                    new DownloadJson1(ImageTextView2Activity.this).execute();
                }else {
                    Toast.makeText(getApplicationContext(), "Not Connect", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    public void LoadjsonData(String sData){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_list);
        dialog.setTitle("읽어온 데이타 정보");
        dialog.setCancelable(true);

        try {
            Gson gson = new Gson();
            DownloadJson.Goods goods[] = gson.fromJson(sData, DownloadJson.Goods[].class);
            List<DownloadJson.Goods> result = Arrays.asList(goods);

            ImageView imageView1 = (ImageView)dialog.findViewById(R.id.imageView1);
            TextView textView1 = (TextView)dialog.findViewById(R.id.textView1);
            TextView textView2 = (TextView)dialog.findViewById(R.id.textView2);
            for(int i=0; i < result.size(); i++){
                textView1.setText(result.get(i).getGoodsName());
                textView2.setText(result.get(i).getAdminID());
                try {
                    DownloadImage downloadImage = new DownloadImage(imageView1);
                    downloadImage.execute(result.get(i).getFileName1());
                }catch (Exception e){
                    imageView1.setImageResource(R.drawable.r);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        dialog.show();
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
