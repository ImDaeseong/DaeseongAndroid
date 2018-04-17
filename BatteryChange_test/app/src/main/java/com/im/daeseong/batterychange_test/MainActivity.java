package com.im.daeseong.batterychange_test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

        ViewGroup rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        floatingTextView = new FloatingTextView(rootView);
        floatingTextView.setText("");
        floatingTextView.getFloatingview().setVisibility(View.VISIBLE);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingTextView.getFloatingview().getLayoutParams();

        //layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        //layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        //layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        //layoutParams.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        //layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        //layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

        //layoutParams.setMargins(0,(screenLength - (screenLength/5)),0,0);
        layoutParams.setMargins(0,(screenLength - (screenLength/3)),0,0);

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
        tvbattery.setText(String.format("Battery Check:%d%%", batteryLevel));

        floatingTextView.setText(tvbattery.getText().toString());
    }

}
