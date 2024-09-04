package com.im.daeseong.singleton_test.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


public class DbHelperAlarm extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "AlarmDB.db";
    public static final String TABLE_ALARM = "alarms";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_WRITEDATE = "writeDate";


    public DbHelperAlarm(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            Log.d("onCreate", "DbHelperAlarm");

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);

            /*
            String createTable = "CREATE TABLE " +
                    TABLE_ALARM + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE + " TEXT,"
                    + COLUMN_CONTENT + " TEXT,"
                    + COLUMN_WRITEDATE + " TEXT" + ")";
            db.execSQL(createTable);
            */

            String createTable = "CREATE TABLE " +
                    TABLE_ALARM + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_TITLE + " TEXT NOT NULL, "
                    + COLUMN_CONTENT + " TEXT NOT NULL, "
                    + COLUMN_WRITEDATE + " DATETIME DEFAULT CURRENT_DATE);";
            db.execSQL(createTable);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            Log.d("onUpgrade", "DbHelperAlarm");

            if(oldVersion == 1 && newVersion == 2){

                db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);

                String createTable = "CREATE TABLE " +
                        TABLE_ALARM + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_TITLE + " TEXT NOT NULL, "
                        + COLUMN_CONTENT + " TEXT NOT NULL, "
                        + COLUMN_WRITEDATE + " DATETIME DEFAULT CURRENT_DATE);";
                db.execSQL(createTable);

            } else  if(oldVersion == 2 && newVersion == 3){

            }

            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);
            //onCreate(db);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void addAlarm(Alarm alarm){

        try {

            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, alarm.getTitle());
            values.put(COLUMN_CONTENT, alarm.getContent());
            //values.put(COLUMN_WRITEDATE, alarm.getWriteDate());

            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TABLE_ALARM, null, values);
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void updateAlarm(Alarm alarm){

        try{

            ContentValues values = new ContentValues();
            values.put(COLUMN_CONTENT, alarm.getContent());
            //values.put(COLUMN_WRITEDATE, alarm.getWriteDate());

            SQLiteDatabase db = this.getWritableDatabase();
            String filter = COLUMN_TITLE + " = \"" + alarm.getTitle() + "\"";
            db.update(TABLE_ALARM, values, filter, null );
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void deleteAlarm(String title){

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            String filter = COLUMN_TITLE + " = \"" + title + "\"";
            db.delete(TABLE_ALARM, filter, null);
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void deleteMaxData(){

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE from alarms where _id = (select min(_id) from alarms);";
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public Alarm getMaxData(){

        Alarm alarm = new Alarm();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "select * from alarms where _id = (select min(_id) from alarms)";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                alarm.setId(Integer.parseInt(cursor.getString(0)));
                alarm.setTitle(cursor.getString(1));
                alarm.setContent(cursor.getString(2));
                alarm.setWriteDate(cursor.getString(3));
                cursor.close();
            } else {
                alarm = null;
            }
            db.close();
        }catch (Exception e){

            e.printStackTrace();
        }
        return alarm;
    }

    public void clearAlarm(){

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_ALARM, "", null);
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public ArrayList<Alarm> getAlarmList(){

        ArrayList<Alarm> list = new ArrayList<>();

        try{

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select _id, title, content, writeDate from "
                    + TABLE_ALARM, null);

            while (cursor.moveToNext()){
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String writeDate = cursor.getString(3);
                Alarm alarm = new Alarm(_id, title, content, writeDate);
                list.add(alarm);
            }
            cursor.close();
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
        return list;
    }

    public int getRowCount(){

        long count = 0;

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            count = DatabaseUtils.queryNumEntries(db, TABLE_ALARM);
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
        return (int)count;
    }

    public boolean findAlarm(String title){

        boolean bFind = false;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_ALARM + " WHERE " +  COLUMN_TITLE + " = \"" + title + "\"";
            Cursor cursor = db.rawQuery(query, null);

            int idValue = 0;
            if (cursor != null) {
                cursor.moveToFirst();
                idValue = cursor.getInt(0);
                cursor.close();
                bFind = true;
            }
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
        return bFind;
    }

    public Alarm getAlarm(String title) {

        Alarm alarm = new Alarm();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_ALARM + " WHERE " + COLUMN_TITLE + " = \"" + title + "\"";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                alarm.setId(Integer.parseInt(cursor.getString(0)));
                alarm.setTitle(cursor.getString(1));
                alarm.setContent(cursor.getString(2));
                alarm.setWriteDate(cursor.getString(3));
                cursor.close();
            } else {
                alarm = null;
            }
            db.close();

        }catch (Exception e){

            e.printStackTrace();
        }
        return alarm;
    }
}

