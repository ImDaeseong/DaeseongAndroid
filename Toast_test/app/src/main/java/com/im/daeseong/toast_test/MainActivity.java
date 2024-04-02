package com.im.daeseong.toast_test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.google.android.material.snackbar.Snackbar;//import android.support.design.widget.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4, button5;
    private ImageView ivbitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivbitmap = (ImageView)findViewById(R.id.ivbitmap);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.toast_layout, (ViewGroup)findViewById(R.id.toastlayout));
                TextView tvtoast = view.findViewById(R.id.tvtoast);
                tvtoast.setText("Toast test");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                //toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
            }

        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Snackbar test", Snackbar.LENGTH_LONG).show();

            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = getBitmapFromView(getWindow().getDecorView());
                if(bitmap != null){
                    ivbitmap.setImageBitmap(bitmap);
                }
            }
        });

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Toast_layout1(MainActivity.this, "ClickToast ClickToast");
            }
        });

        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                View view = findViewById(android.R.id.content);
                Snackbar snackbar = Snackbar.make(view, "Snackbar test", Snackbar.LENGTH_LONG);

                //배경색
                snackbar.getView().setBackgroundColor(Color.WHITE);

                //글자색,가운데정렬
                TextView textView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setTextColor(Color.BLACK);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                snackbar.show();
            }
        });

    }

    private Bitmap getBitmapFromView(View view) {
        try {
            Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(returnedBitmap);
            Drawable bgDrawable = view.getBackground();
            if (bgDrawable != null)
                bgDrawable.draw(canvas);
            else
                canvas.drawColor(Color.WHITE);
            view.draw(canvas);
            return returnedBitmap;
        } catch (Exception e) {
        }
        return null;
    }

}
