package com.daeseong.alarm_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AlarmdbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alarms_db";

    private static final String TABLE_NAME = "alarms";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HOUR = "hour";
    private static final String COLUMN_MINUTE = "minute";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME
                    + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_HOUR + " INTEGER,"
                    + COLUMN_MINUTE + " INTEGER"
                    + ")";

    public AlarmdbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //테이블 생성
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 테이블 존재시 제거
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //테이블 재생성
        onCreate(db);
    }

    public long addAlarm(Alarm alarm) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOUR, alarm.getHour());
        values.put(COLUMN_MINUTE, alarm.getMinute());

        //추가
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long updateAlarm(Alarm alarm) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOUR, alarm.getHour());
        values.put(COLUMN_MINUTE, alarm.getMinute());

        //수정
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?",  new String[]{String.valueOf(alarm.getID())});
        db.close();

        return alarm.getID();
    }

    public void deleteAlarm(long id) {

        SQLiteDatabase db = this.getWritableDatabase();

        //삭제
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAlarmAll() {

        SQLiteDatabase db = this.getWritableDatabase();

        //전체 삭제
        db.execSQL("delete from alarms");
        db.close();
    }

    public Alarm getAlarm(long id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_HOUR, COLUMN_MINUTE },
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null
        );

        Alarm alarm = new Alarm();
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            cursor.moveToFirst();
            alarm = new Alarm(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_HOUR)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_MINUTE))
            );
            cursor.close();
        }
        return alarm;
    }

    public List<Alarm> getAllAlarms() {

        List<Alarm> alarmList = new ArrayList<>();

        String selectAllQuery = "SELECT * FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm();
                alarm.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                alarm.setHour(cursor.getInt(cursor.getColumnIndex(COLUMN_HOUR)));
                alarm.setMinute(cursor.getInt(cursor.getColumnIndex(COLUMN_MINUTE)));
                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return alarmList;
    }

}
