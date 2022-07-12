package com.daeseong.butterknife_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.chk1)
    CheckBox chk1;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.edt1)
    EditText edt1;

    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        tv1.setText("비밀번호를 입력하세요.");
    }

    @OnCheckedChanged(R.id.chk1)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            chk1.setText("checked");
        }else{
            chk1.setText("unchecked");
        }
    }

    @OnClick(R.id.btn1)
    public void button1_Click(View view){
        edt1.setText("1234");
    }
}