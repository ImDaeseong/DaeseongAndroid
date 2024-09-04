package com.daeseong.animation_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Animate7Activity extends AppCompatActivity {

    private static final String TAG = Animate7Activity.class.getSimpleName();

    private LinearLayout llayout;
    private int nWidth, nHeight, nImgHeight;

    private Button button1;
    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate7);

        llayout = (LinearLayout) findViewById(R.id.llayout);

        //해상도 사이즈
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        nWidth = displayMetrics.widthPixels - dip2px(this, 29);
        nHeight = displayMetrics.heightPixels;
        nImgHeight = (int) ((float) ((320) * (float) ((float) nWidth / 960)));

        image1 = (ImageView)findViewById(R.id.image1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAnimation1(image1);
            }
        });

    }

    private void runAnimation1(ImageView imageView) {

        try {

            //ImageView - > Bitmap
            Drawable drawable = imageView.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            int rows = 3;
            int columns = 5;
            int xPosition = 0;
            int yPosition = 0;

            int nWidth = bitmap.getWidth() / columns;
            int nHeight = bitmap.getHeight() / rows;

            for(int i=0; i < 14; i++ ){

                xPosition = (i % columns) * nWidth;
                yPosition = (i / columns) * nHeight;

                //이미지 자르기
                Bitmap croppedBmp = Bitmap.createBitmap(bitmap, xPosition, yPosition, nWidth, nHeight);
                setImageView(i, croppedBmp);
            }

        }catch (Exception ex){
        }
    }

    private void setImageView(int nIndex, Bitmap bitmap) {

        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(nImgHeight * 2, nImgHeight);

        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(params);

        //마진 설정
        if (nIndex == 0){
            params.setMargins(0,0,0,0);
        }else {
            params.setMargins(10,0,0,0);
        }
        imageView.setImageBitmap(bitmap);

        llayout.addView(imageView);
    }

    private static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
