package com.im.daeseong.sharedpreferences_test;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.im.daeseong.sharedpreferences_test.util.SharedPreferences_util;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private  int REQUEST_DATA = 1;
    private  int RESULT_OK = 0;

    EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);

         try {

             //조회
             String sLoadID = (String) SharedPreferences_util.getValue(this, "sID", "");
             String sLoadPassword = (String) SharedPreferences_util.getValue(this, "sPassword", "");
             int sLoadMemberNumber = (int) SharedPreferences_util.getValue(this, "sMemberNumber", 1);
             boolean sLoadSaved = (boolean) SharedPreferences_util.getValue(this, "sSaved", false);
             String sLoadtemp = (String) SharedPreferences_util.getValue(this, "stemp", "");

             //Log.d("sLoadID", sLoadID);
             //Log.d("sLoadPassword", sLoadPassword);
             //Log.d("sLoadMemberNumber", String.valueOf(sLoadMemberNumber));
             //Log.d("sLoadtemp", sLoadtemp);

             editText1.setText(sLoadID);
             editText2.setText(sLoadPassword);
             editText3.setText(String.valueOf(sLoadMemberNumber));

         }catch (Exception e){
             e.printStackTrace();
         }
    }

    public void btn_Save(View v){

        String sID = editText1.getText().toString();
        String sPassword = editText2.getText().toString();
        String sMemberNumber = editText3.getText().toString();

        if(TextUtils.isEmpty(sID)){
            Log.d("sID", "null");
            return;
        }

        if(TextUtils.isEmpty(sPassword)){
            Log.d("sPassword", "null");
            return;
        }

        if(TextUtils.isEmpty(sMemberNumber)){
            Log.d("sMemberNumber", "null");
            return;
        }

        //저장
        SharedPreferences_util.setValue(this, "sID", sID);
        SharedPreferences_util.setValue(this, "sPassword", sPassword);
        SharedPreferences_util.setValue(this, "sMemberNumber", Integer.parseInt(sMemberNumber));
        SharedPreferences_util.setValue(this, "sSaved", true);
        SharedPreferences_util.setValue(this, "stemp", "");

        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText1.requestFocus();
    }

    public void btn_Remove(View v){

        //전체 삭제
        Map<String, ?> alls = SharedPreferences_util.getAll(this);
        for(String sKey : alls.keySet()){
            Log.d("sKey", sKey);
            SharedPreferences_util.remove(this,  sKey);
        }

        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText1.requestFocus();
    }

    public void btn_Get(View v){

        /*
        //단순 Activity 호출
        Intent intent = new Intent(this, SharedPreferencesActivity.class);
        startActivity(intent);
        */

        //Activity 호출후 결과값 받음
        String sLoadID = (String) SharedPreferences_util.getValue(this, "sID", "");
        String sLoadPassword = (String) SharedPreferences_util.getValue(this, "sPassword", "");
        int sLoadMemberNumber = (int) SharedPreferences_util.getValue(this, "sMemberNumber", 1);
        boolean sLoadSaved  = (boolean) SharedPreferences_util.getValue(this, "sSaved", false);
        String sLoadtemp = (String) SharedPreferences_util.getValue(this, "stemp", "");

        if(TextUtils.isEmpty(sLoadID)){
            Log.d("sID", "null");
            return;
        }

        if(TextUtils.isEmpty(sLoadPassword)){
            Log.d("sPassword", "null");
            return;
        }

        if(TextUtils.isEmpty(String.valueOf(sLoadMemberNumber))){
            Log.d("sMemberNumber", "null");
            return;
        }

        Intent intent = new Intent(this, SharedResultActivity.class);
        intent.putExtra("sID", sLoadID);
        intent.putExtra("sPassword", sLoadPassword);
        intent.putExtra("sMemberNumber", sLoadMemberNumber);
        intent.putExtra("sSaved", sLoadSaved);
        intent.putExtra("stemp", sLoadtemp);
        startActivityForResult(intent, REQUEST_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_DATA)
        {
            if(resultCode == RESULT_OK){

                String sID = data.getStringExtra("sID");
                String sPassword = data.getStringExtra("sPassword");
                String sMemberNumber = data.getStringExtra("sMemberNumber");

                //Log.d("sID", sID);
                //Log.d("sPassword", sPassword);

                editText1.setText(sID);
                editText2.setText(sPassword);
                editText3.setText(sMemberNumber);

                //저장
                SharedPreferences_util.setValue(this, "sID", sID);
                SharedPreferences_util.setValue(this, "sPassword", sPassword);
                SharedPreferences_util.setValue(this, "sMemberNumber", Integer.parseInt(sMemberNumber));

                Toast.makeText(this, "변경된 내용이 저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btn_Clear(View v){

        //전체 해제
        SharedPreferences_util.clear(this);

        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText1.requestFocus();
    }

}
