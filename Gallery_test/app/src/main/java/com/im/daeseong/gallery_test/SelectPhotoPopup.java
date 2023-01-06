package com.im.daeseong.gallery_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SelectPhotoPopup extends AppCompatActivity {

    private static final String TAG = SelectPhotoPopup.class.getSimpleName();

    private static int screenwidth;
    private static int screenLength;
    private View clClose;
    private GridView grView;
    private PopupViewAdapter imageViewAdapter;
    private ProgressBar pProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_select_photo_popup);

        setFinishOnTouchOutside(false);  //다른 영역 클릭 방지

        init();

        changeDisplay();

        loadData();
    }

    private void loadData(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        initImages();

                        /*
                        Bitmap bitmap;
                        String title;

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
                        title = "title1";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);
                        title = "title2";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg3);
                        title = "title3";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg4);
                        title = "title4";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg5);
                        title = "title5";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg6);
                        title = "title6";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg7);
                        title = "title7";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));

                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg8);
                        title = "title8";
                        imageViewAdapter.addPhoto(new ImageItem(bitmap, title));
                        */

                        pProgressBar.setVisibility(View.GONE);
                    }
                });
            }

        }, 1000);
    }

    private void initImages(){

        for (String item : SearchImage.getAllPicture()){
            Bitmap bitmap = SearchImage.loadBitmap(item);
            String title = SearchImage.getFileName(item);
            imageViewAdapter.addPhoto(new ImageItem(bitmap, title));
        }
    }

    private void changeDisplay(){

        try {
            Display display = getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            screenLength = size.y;
            screenwidth = size.x;

            if(screenLength > 0 && screenwidth > 0){

                int ViewWidth = (screenwidth - dip2px(this, 56));
                getWindow().setLayout(ViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

                WindowManager.LayoutParams WMLP = getWindow().getAttributes();
                WMLP.gravity = Gravity.CENTER;
                WMLP.x = 0;
                WMLP.y = 0;
                getWindow().setAttributes(WMLP);
            }

        }catch (Exception e){
        }
    }

    private void init(){

        grView = (GridView)findViewById(R.id.grView);
        imageViewAdapter = new PopupViewAdapter(this);
        grView.setAdapter(imageViewAdapter);
        grView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                    Toast.makeText(SelectPhotoPopup.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                }
            }
        });

        //닫기
        clClose = (View)findViewById(R.id.clClose);
        clClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pProgressBar = (ProgressBar)findViewById(R.id.progressbar1);
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
