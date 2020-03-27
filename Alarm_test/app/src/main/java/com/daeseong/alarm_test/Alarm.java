package com.daeseong.alarm_test;

public class Alarm {

    private int ID;
    private int nHour;
    private int nMinute;

    public Alarm(){
    }

    public Alarm(int id, int nHour, int nMinute){
        this.ID = id;
        this.nHour = nHour;
        this.nMinute = nMinute;
    }

    public void setID(int ID) {
        this.ID = ID;
}

    public void setHour(int nHour) {
        this.nHour = nHour;
    }

    public void setMinute(int nMinute) {
        this.nMinute = nMinute;
    }

    public int getID() {
        return this.ID;
    }

    public int getHour() {
        return nHour;
    }

    public int getMinute() {
        return nMinute;
    }

}
