package com.daeseong.animation_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;


import android.os.Handler;

import java.util.ArrayList;
import java.util.TimerTask;

public class spriteTask extends TimerTask {

    private static final String TAG = spriteTask.class.getSimpleName();

    private Context context;
    private Handler handler = null;
    private ImageView imageView;
    private ArrayList<Bitmap> list = null;
    private int nIndex = 0;

    public spriteTask(Context context, ImageView imageView){
        this.context = context;
        this.imageView = imageView;
        handler = new Handler();
        list = new ArrayList<Bitmap>();

        initBitmap();
    }

    public void run() {
        handler.post(new Runnable() {
            public void run() {
                changeImage();
            }});
    }

    private void changeImage(){

        if(nIndex >= 14){
            nIndex = 0;
        }

        //Log.e(TAG, "nIndex:" + nIndex);

        imageView.setImageBitmap(list.get(nIndex));
        nIndex++;
    }

    private void initBitmap(){

        //14개의 이미지
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite);

        int rows = 3;
        int columns = 5;
        int xPosition = 0;
        int yPosition = 0;

        int nWidth = bitmap.getWidth() / columns;
        int nHeight = bitmap.getHeight() / rows;

        for(int i=0; i < 14; i++ ){

            xPosition = (i % columns) * nWidth;
            yPosition = (i / columns) * nHeight;

            //이미지 쪼개서 리스트에 저장

            //Bitmap -> imageView
            Bitmap croppedBmp = Bitmap.createBitmap(bitmap, xPosition, yPosition, nWidth, nHeight);
            list.add(croppedBmp);
        }
    }

}
