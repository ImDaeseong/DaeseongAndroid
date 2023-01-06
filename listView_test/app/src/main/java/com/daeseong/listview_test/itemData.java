package com.daeseong.listview_test;

public class itemData {

    private String sLocName;
    private double dLatitude;
    private double dLongitude;

    public itemData(String sLocName, double dLatitude, double dLongitude){
        this.sLocName = sLocName;
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;
    }

    public String getLocName() {
        return sLocName;
    }

    public double getLatitude() {
        return dLatitude;
    }

    public double getLongitude() {
        return dLongitude;
    }
}
