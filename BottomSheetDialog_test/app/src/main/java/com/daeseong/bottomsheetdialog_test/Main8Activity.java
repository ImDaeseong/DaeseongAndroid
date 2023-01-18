package com.daeseong.bottomsheetdialog_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Main8Activity extends AppCompatActivity {

    private static final String TAG = Main8Activity.class.getSimpleName();

    private View cL1;
    private Button btn1_1, btn2_1, btn3_1;

    private View cL2;
    private EditText et1, et2;
    private Button btn1;

    private boolean bClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        init();

        cL1 = findViewById(R.id.cL1);
        cL2 = findViewById(R.id.cL2);

        setVisibleView();

        btn1_1 = findViewById(R.id.btn1_1);
        btn1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bClick = !bClick;
                setVisibleView();
            }
        });

        btn2_1 = findViewById(R.id.btn2_1);
        btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bClick = !bClick;
                setVisibleView();
            }
        });

        btn3_1 = findViewById(R.id.btn3_1);
        btn3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bClick = !bClick;
                setVisibleView();
            }
        });

        et1 = (EditText) findViewById(R.id.et1);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et2 = (EditText) findViewById(R.id.et2);
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        //Log.e(TAG, "onBackPressed");

        changeView();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN  && isOutOfBounds(this, event)) {

            //Log.e(TAG, "다이얼로그 영역밖 터치");

            changeView();
        }

        return true;
    }

    private void setVisibleView(){

        if(bClick){

            cL1.setVisibility(View.GONE);
            cL2.setVisibility(View.VISIBLE);

        }else {

            cL1.setVisibility(View.VISIBLE);
            cL2.setVisibility(View.GONE);
        }
    }

    private void init(){

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        getWindow().setGravity(Gravity.BOTTOM);
    }

    private void changeView(){

        if(bClick){
            bClick = false;
            setVisibleView();
        } else {
            finish();
        }
    }

    private boolean isOutOfBounds(Activity context, MotionEvent event) {

        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = context.getWindow().getDecorView();
        return (x < -slop) || (y < -slop)
                || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }
}