package com.im.daeseong.db_test.SQLiteOpenHelper;

public class Alarm {

    private int _id;
    private String title;
    private String content;
    private String writeDate;

    public Alarm(){
    }

    public Alarm(int id, String title, String content, String writeDate){
        this._id = id;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
    }

    public Alarm(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void setId(int id){
        this._id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setWriteDate(String writeDate){
        this.writeDate = writeDate;
    }

    public int getId(){
        return this._id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public String getWriteDate(){
        return this.writeDate;
    }
}
