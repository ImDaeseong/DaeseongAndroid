package com.im.daeseong.batterychange_test;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Point;
import androidx.constraintlayout.widget.ConstraintLayout;//import android.support.constraint.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;//import android.support.design.widget.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BatteryReceiver mReceiver;

    private TextView tvbattery;
    private FloatingActionButton floatbtn;

    private FloatingTextView floatingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvbattery = (TextView)findViewById(R.id.tvbattery);

        addFloatingView();


        floatbtn = (FloatingActionButton)findViewById(R.id.floatbtn);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "FloatingActionButton click", Toast.LENGTH_SHORT).show();
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        mReceiver = new BatteryReceiver();
        registerReceiver(mReceiver, intentFilter);
        mReceiver.setBatteryListener(new BatteryReceiver.BatteryListener() {
            @Override
            public void onListener(int batteryLevel) {
                setBatteryInfo(batteryLevel);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);
    }

    private void addFloatingView(){

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        int screenLength = size.y;

        ViewGroup rootView = (ViewGroup)findViewById(R.id.clMain);
        floatingTextView = new FloatingTextView(rootView);
        floatingTextView.setText("");
        floatingTextView.getFloatingview().setVisibility(View.VISIBLE);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingTextView.getFloatingview().getLayoutParams();
        //layoutParams.setMargins(0, (screenLength - dip2px(this, 120)),0,0);
        layoutParams.setMargins(0, (screenLength - dip2px(this, 210)),0,0);

        floatingTextView.getFloatingview().setLayoutParams(layoutParams);
        rootView.addView(floatingTextView.getFloatingview());
    }

    private void hideFloatingView(){
        floatingTextView.getFloatingview().setVisibility(View.GONE);
    }

    private void setBatteryInfo(int batteryLevel){
        if (batteryLevel >= 50) {
            tvbattery.setTextColor(Color.GREEN);
        }
        else if (batteryLevel >= 30) {
            tvbattery.setTextColor(Color.YELLOW);
        }
        else {
            tvbattery.setTextColor(Color.RED);
        }
        tvbattery.setText(String.format("밧데리 잔량:%d%%", batteryLevel));

        floatingTextView.setText(tvbattery.getText().toString());
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

}
