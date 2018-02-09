package com.im.daeseong.singleton_test.Db;

import android.content.Context;

import java.util.ArrayList;

public class DbHandler {

    private DbHelperAlarm dbHelperAlarm;

    private static DbHandler instance;
    public static DbHandler getInstance(){
        if (instance == null) {
            instance = new DbHandler();
        }
        return instance;
    }

    public void init(Context context){
        dbHelperAlarm = new DbHelperAlarm(context, null, null, 1);
    }

    public void addAlarm(Alarm alarm){
        dbHelperAlarm.addAlarm(alarm);
    }

    public void updateAlarm(Alarm alarm){
        dbHelperAlarm.updateAlarm(alarm);
    }

    public void deleteAlarm(String title){
        dbHelperAlarm.deleteAlarm(title);
    }

    public void deleteMaxData(){
        dbHelperAlarm.deleteMaxData();
    }

    public Alarm getMaxData(){
        return dbHelperAlarm.getMaxData();
    }

    public void clearAlarm(){
        dbHelperAlarm.clearAlarm();
    }

    public ArrayList<Alarm> getAlarmList(){
        return dbHelperAlarm.getAlarmList();
    }

    public int getRowCount(){
        return dbHelperAlarm.getRowCount();
    }

    public boolean findAlarm(String title){
        return dbHelperAlarm.findAlarm(title);
    }

    public Alarm getAlarm(String title) {
        return dbHelperAlarm.getAlarm(title);
    }
}

