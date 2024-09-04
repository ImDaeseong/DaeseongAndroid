package com.daeseong.contextmenu_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class Popupmenu2Activity extends AppCompatActivity {

    private static final String TAG = Popupmenu2Activity.class.getSimpleName();

    private Button button1;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupmenu2);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        //팝업 생성성
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //외부 영역 클릭시 dismiss() 호출
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        Button btnMenu1 = popupView.findViewById(R.id.btnMenu1);
        btnMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "btnMenu1");

                popupWindow.dismiss();
            }
        });

        Button btnMenu2 = popupView.findViewById(R.id.btnMenu2);
        btnMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "btnMenu2");

                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(button1);
    }

}