package com.im.daeseong.singleton_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.im.daeseong.singleton_test.Db.Alarm;
import com.im.daeseong.singleton_test.Db.DbHandler;
import com.im.daeseong.singleton_test.util.Screen_util;
import com.im.daeseong.singleton_test.util.SharedPreferences_util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int nWidth = Screen_util.getInstance().getScreenWidthPx();
        int nHeight = Screen_util.getInstance().getScreenHeightPx();
        Log.e(TAG, "ScreenWidthPx:" + String.valueOf(nWidth) + " ScreenHeightPx:" + String.valueOf(nHeight) );


        String sID = (String) SharedPreferences_util.getInstance().getValue("ID", "");
        if(sID == ""){
            SharedPreferences_util.getInstance().setValue("ID", "id");
        } else {
            SharedPreferences_util.getInstance().remove("ID");
        }
        Log.e(TAG, "ID:" + sID );

        int nCount = DbHandler.getInstance().getRowCount();
        Log.e(TAG, "getRowCount:" + String.valueOf(nCount) );

        //Add();
        //delete();
    }

    private void Add(){
        try {
            Alarm alarm = new Alarm("title", "content");
            DbHandler.getInstance().addAlarm(alarm);
            Log.e(TAG, "DbHandler:" + "Add Test" );

            //입력 데이타가 10개가 넘으면 가장 처음 입력한 데이타 삭제
            int nCount = DbHandler.getInstance().getRowCount();
            if(nCount > 10){
                DbHandler.getInstance().deleteMaxData();
            }
            read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void read(){
        try {
            ArrayList<Alarm> alarmList = DbHandler.getInstance().getAlarmList();
            String sMsg = "";
            for (Alarm alarm : alarmList) {
                sMsg += "id : " + alarm.getId() + " / title : " + alarm.getTitle() + " / content : " + alarm.getContent() + " / WriteDate : " + alarm.getWriteDate() + "\n";
            }
            Log.e(TAG, "DbHandler read:" + sMsg );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void delete() {
        try {
            DbHandler.getInstance().deleteAlarm("title");
            Log.e(TAG, "DbHandler:" + "delete Test" );
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
