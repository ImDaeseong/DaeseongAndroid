package com.im.daeseong.db_test;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import com.im.daeseong.db_test.SQLiteOpenHelper.Alarm;
import com.im.daeseong.db_test.SQLiteOpenHelper.DbHelperAlarm;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTitle, editContent;
    Button  btnAdd, btnRead, btnUpdate, btnDelete;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editContent = (EditText) findViewById(R.id.editContent);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        textResult = (TextView) findViewById(R.id.textResult);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        try {

            DbHelperAlarm dbHelperAlarm = new DbHelperAlarm(this, null, null, 1);
            int nCount = dbHelperAlarm.getRowCount();
            String sCount = "개수:" + String.valueOf(nCount);
            textResult.setText(sCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                Add();
                break;
            case R.id.btnRead:
                read();
                break;
            case R.id.btnUpdate:
                update();
                break;
            case R.id.btnDelete:
                delete();
                break;
        }
    }

    private void Add(){

        try {
            String title = editTitle.getText().toString();
            String content = editContent.getText().toString();

            Alarm alarm = new Alarm(title, content);
            DbHelperAlarm dbHelperAlarm = new DbHelperAlarm(this, null, null, 1);
            dbHelperAlarm.addAlarm(alarm);


            //입력 데이타가 10개가 넘으면 가장 처음 입력한 데이타 삭제
            int nCount = dbHelperAlarm.getRowCount();
            if(nCount > 10){
                dbHelperAlarm.deleteMaxData();
            }

            editTitle.setText("");
            editContent.setText("");
            editTitle.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read(){

        try {

            DbHelperAlarm dbHelperAlarm = new DbHelperAlarm(this, null, null, 1);
            ArrayList<Alarm> alarmList = dbHelperAlarm.getAlarmList();

            String sMsg = "";
            for (Alarm alarm : alarmList) {
                sMsg += "id : " + alarm.getId() + " / title : " + alarm.getTitle() + " / content : " + alarm.getContent() + " / WriteDate : " + alarm.getWriteDate() + "\n";
            }
            textResult.setText(sMsg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(){

        try {
            String title = editTitle.getText().toString();
            String content = editContent.getText().toString();

            DbHelperAlarm dbHelperAlarm = new DbHelperAlarm(this, null, null, 1);

            if (dbHelperAlarm.findAlarm(title)) {

                Alarm alarm = dbHelperAlarm.getAlarm(title);
                String utitle = alarm.getTitle();
                //String  ucontent = alarm.getContent();
                //String  uwriteDate = alarm.getWriteDate();
                /*
                //현재 시간
                long lNow = System.currentTimeMillis();
                Date date = new Date(lNow);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentTime = sdf.format(date);
                */

                dbHelperAlarm.updateAlarm(new Alarm(utitle, content));

                editTitle.setText("");
                editContent.setText("");
                editTitle.requestFocus();

                //update 후 데이타 확인
                read();

            } else {
                textResult.setText("데이타 미존재");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete() {

        try {

            String title = editTitle.getText().toString();

            DbHelperAlarm dbHelperAlarm = new DbHelperAlarm(this, null, null, 1);
            dbHelperAlarm.deleteAlarm(title);

            editTitle.setText("");
            editTitle.requestFocus();

            //delete 후 데이타 확인
            read();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        //가장 처음 입력한 데이타 조회
        DbHelperAlarm dbHelperAlarm = new DbHelperAlarm(this, null, null, 1);
        Alarm alarm = dbHelperAlarm.getMaxData();

        String sMsg = "";
        sMsg += "id : " + alarm.getId() + " / title : " + alarm.getTitle() + " / content : " + alarm.getContent() + " / WriteDate : " + alarm.getWriteDate() + "\n";
        textResult.setText(sMsg);
        */

        /*
        //전체 데이타 삭제
        try {

            DbHelperAlarm dbHelperAlarm = new DbHelperAlarm(this, null, null, 1);
            dbHelperAlarm.clearAlarm();

            editTitle.setText("");
            editTitle.requestFocus();

            //delete 후 데이타 확인
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
