package com.im.daeseong.checkbox_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class Check1Activity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener{

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check1);

        checkBox = (CheckBox)findViewById(R.id.checkBox1);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            checkBox.setText("checked");
        }else{
            checkBox.setText("unchecked");
        }
    }
}
