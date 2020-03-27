package com.daeseong.alarm_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private Button button1, button2, button3;

    private ListView listview1;
    private AlarmAdapter alarmAdapter;

    private AlarmdbHelper alarmdbHelper;

    private TimePicker timePicker;
    private int nhour;
    private int nMinute;

    private int selectID = 0;


    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        initFilter();

        //알람 설정 초기화
        Alarm_util.getInstance().init(this);

        //DB 초기화
        alarmdbHelper = new AlarmdbHelper(this);

        //Adapter 선언
        alarmAdapter = new AlarmAdapter();

        //db 데이터 전체 삭제
        //alarmdbHelper.deleteAlarmAll();

        //데이터 베이스 저장 항목 리스트 설정 - 앱 재시작시 데이터베이스 정보에 맞게 재등록 필요(재등록시 삭제후 재등록)
        List<Alarm> list = alarmdbHelper.getAllAlarms();
        for(int i=0; i< list.size(); i++){

            //리스트뷰
            alarmAdapter.addItem(list.get(i).getID(), list.get(i).getHour(), list.get(i).getMinute());

            //알람 서비스 삭제
            Alarm_util.getInstance().DeleteAlarm(list.get(i).getID());

            //알람 서비스 등록
            Alarm_util.getInstance().AddAlaram(list.get(i).getID(), list.get(i).getHour(), list.get(i).getMinute());
        }


        //listview 설정
        listview1 = (ListView)findViewById(R.id.listview1);
        listview1.setAdapter(alarmAdapter);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Alarm item = (Alarm)parent.getItemAtPosition(position);
                selectID = item.getID();
                //Log.e(TAG, "id:" + item.getID() + " hour:" + item.getHour() + " minute:" + item.getMinute() );

            }
        });


        //시간 설정
        timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(false);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    nhour = timePicker.getHour();
                    nMinute = timePicker.getMinute();
                } else {
                    nhour = timePicker.getCurrentHour();
                    nMinute = timePicker.getCurrentMinute();
                }

                //Log.e(TAG, "nhour:" + nhour + " nMinute:" + nMinute);
                //Log.e(TAG, "hourOfDay:" + hourOfDay + " minute:" + minute);
            }
        });


        //추가
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nhour > 0) {

                    //db 추가
                    Alarm alarm = new Alarm(0, nhour, nMinute);
                    long id = alarmdbHelper.addAlarm(alarm);

                    //알람 서비스 등록
                    Alarm_util.getInstance().AddAlaram((int) id, nhour, nMinute);

                    //리스트뷰 등록
                    alarmAdapter.addItem((int) id, nhour, nMinute);
                    alarmAdapter.notifyDataSetChanged();

                    String sMsg = String.format("알람 서비스 등록 %d시 %d분 호출 예정", nhour, nMinute);
                    Toast.makeText(getApplicationContext(), sMsg,  Toast.LENGTH_SHORT).show();
                }

            }
        });


        //수정
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( selectID > 0 ){

                    Alarm alarm = new Alarm(selectID, nhour, nMinute);
                    long id = alarmdbHelper.updateAlarm(alarm);
                    if( (int)id == selectID) {

                        //알람 서비스 삭제 후 재등록
                        Alarm_util.getInstance().DeleteAlarm(selectID);
                        Alarm_util.getInstance().AddAlaram(selectID, nhour, nMinute);

                        //리스트뷰 업데이트
                        alarmAdapter.updateItem(selectID, nhour, nMinute);
                        alarmAdapter.notifyDataSetChanged();

                        selectID = 0;

                        String sMsg = String.format("수정된 알람 서비스 등록 %d시 %d분 호출 예정", nhour, nMinute);
                        Toast.makeText(getApplicationContext(), sMsg,  Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        //삭제
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( selectID > 0 ){

                    //db 삭제
                    alarmdbHelper.deleteAlarm(selectID);

                    //알람 서비스 삭제
                    Alarm_util.getInstance().DeleteAlarm(selectID);

                    //리스트뷰 삭제
                    alarmAdapter.removeItem(selectID);
                    alarmAdapter.notifyDataSetChanged();

                    selectID = 0;
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DestoryFilter();
    }

    private void initFilter(){

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(intent.getAction().equals("com.daeseong.alarm_test.ID")) {

                    int receiveID = intent.getExtras().getInt("alarmID");
                    Log.e(TAG, "receiveID:" + receiveID);

                    //db 삭제
                    alarmdbHelper.deleteAlarm(receiveID);

                    //알람 서비스 삭제
                    Alarm_util.getInstance().DeleteAlarm(receiveID);

                    //리스트뷰 삭제
                    alarmAdapter.removeItem(receiveID);
                    alarmAdapter.notifyDataSetChanged();

                }
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.daeseong.alarm_test.ID");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void DestoryFilter(){
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

}
