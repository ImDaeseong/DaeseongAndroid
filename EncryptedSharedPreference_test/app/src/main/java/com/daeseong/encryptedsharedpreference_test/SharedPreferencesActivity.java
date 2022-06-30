package com.daeseong.encryptedsharedpreference_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.daeseong.encryptedsharedpreference_test.util.encryptedsharedpreference_util;

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
            String sLoadID = (String) encryptedsharedpreference_util.getInstance(this).getValue("sID", "");
            String sLoadPassword = (String) encryptedsharedpreference_util.getInstance(this).getValue("sPassword", "");
            int sLoadMemberNumber = (int) encryptedsharedpreference_util.getInstance(this).getValue("sMemberNumber", -1);
            boolean sLoadSaved = (boolean) encryptedsharedpreference_util.getInstance(this).getValue("sSaved", false);
            String sLoadtemp = (String) encryptedsharedpreference_util.getInstance(this).getValue("stemp", "");

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