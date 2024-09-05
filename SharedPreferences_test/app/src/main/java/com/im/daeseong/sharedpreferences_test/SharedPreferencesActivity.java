package com.im.daeseong.sharedpreferences_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import com.im.daeseong.sharedpreferences_test.util.SharedPreferences_util;

public class SharedPreferencesActivity extends AppCompatActivity {

    EditText editText5, editText6, editText7, editText8, editText9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);
        editText8 = (EditText)findViewById(R.id.editText8);
        editText9 = (EditText)findViewById(R.id.editText9);

        try {
            //조회
            String sLoadID = (String) SharedPreferences_util.getValue(this, "sID", "");
            String sLoadPassword = (String) SharedPreferences_util.getValue(this, "sPassword", "");
            int sLoadMemberNumber = (int) SharedPreferences_util.getValue(this, "sMemberNumber", -1);
            boolean sLoadSaved = (boolean) SharedPreferences_util.getValue(this, "sSaved", false);
            String sLoadtemp = (String) SharedPreferences_util.getValue(this, "stemp", "");

            //Log.d("sLoadID", sLoadID);
            //Log.d("sLoadPassword", sLoadPassword);
            //Log.d("sLoadMemberNumber", String.valueOf(sLoadMemberNumber));
            //Log.d("sLoadtemp", sLoadtemp);

            if (sLoadSaved)
                editText8.setText("true");
            else
                editText8.setText("false");

            editText5.setText(sLoadID);
            editText6.setText(sLoadPassword);
            editText7.setText(String.valueOf(sLoadMemberNumber));
            editText9.setText(sLoadtemp);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
