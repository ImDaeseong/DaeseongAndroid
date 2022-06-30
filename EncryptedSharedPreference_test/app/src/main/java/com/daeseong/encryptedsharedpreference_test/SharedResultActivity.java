package com.daeseong.encryptedsharedpreference_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SharedResultActivity extends AppCompatActivity {

    private  int REQUEST_DATA = 1;
    private  int RESULT_OK = 0;

    EditText editText10, editText11, editText12, editText13, editText14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_result);

        editText10 = (EditText)findViewById(R.id.editText10);
        editText11 = (EditText)findViewById(R.id.editText11);
        editText12 = (EditText)findViewById(R.id.editText12);
        editText13 = (EditText)findViewById(R.id.editText13);
        editText14 = (EditText)findViewById(R.id.editText14);

        try{
            Intent intent = getIntent();
            String sLoadID = intent.getStringExtra("sID");
            String sLoadPassword = intent.getStringExtra("sPassword");
            int sLoadMemberNumber = intent.getIntExtra("sMemberNumber", 1);
            boolean sLoadSaved  = intent.getBooleanExtra("sSaved", false);
            String sLoadtemp = intent.getStringExtra("stemp");

            //Log.d("sLoadID", sLoadID);
            //Log.d("sLoadPassword", sLoadPassword);
            //Log.d("sLoadMemberNumber", String.valueOf(sLoadMemberNumber));
            //Log.d("sLoadtemp", sLoadtemp);

            if (sLoadSaved)
                editText13.setText("true");
            else
                editText13.setText("false");

            editText10.setText(sLoadID);
            editText11.setText(sLoadPassword);
            editText12.setText(String.valueOf(sLoadMemberNumber));
            editText14.setText(sLoadtemp);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private  void ChangeData(){
        String sID = editText10.getText().toString();
        String sPassword = editText11.getText().toString();
        String sLoadMemberNumber = editText12.getText().toString();

        if(TextUtils.isEmpty(sID)){
            Log.d("sID", "null");
            return;
        }

        if(TextUtils.isEmpty(sPassword)){
            Log.d("sPassword", "null");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("sID", sID);
        intent.putExtra("sPassword", sPassword);
        intent.putExtra("sMemberNumber", sLoadMemberNumber);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void btn_change(View v){
        ChangeData();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //백버튼 기능 막음
        return;
    }
}