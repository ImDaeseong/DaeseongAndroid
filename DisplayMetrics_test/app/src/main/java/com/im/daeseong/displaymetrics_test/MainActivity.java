package com.im.daeseong.displaymetrics_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private TextView tv1, tv2, tv3;

    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);



        findRealSizeA();
        findRealSizeB();

        setScreenInfo(this);

        //ChangeLoc();
        //checkDisplay();
        //setScreenInfo(this);
        //setNoSoftKeyScreenInfo(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //Using Display
    private void findRealSizeA()
    {
        Point size = new Point();
        Display display = this.getWindowManager().getDefaultDisplay();

        if (Build.VERSION.SDK_INT >= 17)
            display.getRealSize(size);
        else
            display.getSize(size);

        int realWidth = size.x;
        int realHeight = size.y;

        Log.i("LOG_TAG", "realWidth: " + realWidth + " realHeight: " + realHeight);
    }

    //Using DisplayMetrics:
    private void findRealSizeB()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= 17)
        {
            this.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        else
        {
            this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        int realWidth = displayMetrics.widthPixels;
        int realHeight = displayMetrics.heightPixels;

        Log.i("LOG_TAG", "realWidth: " + realWidth + " realHeight: " + realHeight);
    }



    private void ChangeLoc(){

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        float imagensizeHeight = (dpHeight *300) / 592;
        float imagensizeWidth = (dpWidth * 300) / 360;
        tv1.getLayoutParams().height = (int)(imagensizeHeight * displayMetrics.density);
        tv1.getLayoutParams().width = (int)(imagensizeWidth * displayMetrics.density);
        ConstraintLayout.LayoutParams params= (ConstraintLayout.LayoutParams) tv1.getLayoutParams();
        params.setMargins((int)(30*displayMetrics.density),(int)(25*displayMetrics.density),(int)(30*displayMetrics.density),0);
        tv1.setLayoutParams(params);
    }

    private void checkDisplay()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        StringBuilder sb = new StringBuilder();
        sb.append("< Display Information >\n\n");
        sb.append("xdpi = ").append(displayMetrics.xdpi).append("\n");
        sb.append("ydpi = ").append(displayMetrics.ydpi).append("\n");
        sb.append("scaledDensity = ").append(displayMetrics.scaledDensity).append("\n");
        sb.append("densityDpi = ").append(displayMetrics.densityDpi).append("\n");
        sb.append("density = ").append(displayMetrics.density).append("\n");
        sb.append("widthPixels = ").append(displayMetrics.widthPixels).append("\n");
        sb.append("heightPixels = ").append(displayMetrics.heightPixels).append("\n");
        tv1.setText(sb.toString());

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        tv2.setText("x=>"+width+",y=>"+height);
    }

     // 소프트키를 미포함한 화면 전체해상도를 가져온다.
    public void setNoSoftKeyScreenInfo(Context context){

        DisplayMetrics dmath=context.getResources().getDisplayMetrics();	// 화면의 가로,세로 길이를 구할 때 사용합니다.
        int width = dmath.widthPixels;
        int height = dmath.heightPixels;
        tv3.setText("x=>"+width+",y=>"+height);
    }

    // 소프트키를 존재여부 체크
    public static boolean isScreenSoftKey(Context context) {

        boolean isKey = false;

        if (Build.VERSION.SDK_INT >= 14) {

            boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (!hasMenuKey && !hasBackKey) {
                isKey = true;
            } else {
                isKey = false;
            }
        } else {
            isKey = false;
        }
        return isKey;
    }

     // 소프트키를 포함한 화면 전체해상도를 가져온다.
    public  void setScreenInfo(Context context){

        int width, height;

        if(Build.VERSION.SDK_INT>=14)
        {
            android.view.Display display = ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
            Point realSize = new Point();
            try {
                Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
            } catch (Exception e){
                e.printStackTrace();
            }
            width=realSize.x;
            height=realSize.y;

        }else{

            DisplayMetrics dmath=context.getResources().getDisplayMetrics();	// 화면의 가로,세로 길이를 구할 때 사용합니다.
            width=dmath.widthPixels;
            height=dmath.heightPixels;
        }

        tv3.setText("x=>"+width+",y=>"+height);
    }

}
