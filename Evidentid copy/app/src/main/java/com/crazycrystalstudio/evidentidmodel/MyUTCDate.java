package com.crazycrystalstudio.evidentidmodel;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

public class MyUTCDate {
    private String pretty;
    private int year;
    private int mon;
    private int mday;
    private int hour;
    private int min;
    private String tzname;

    public MyUTCDate() {
    }

    public MyUTCDate(String pretty, int year, int mon, int mday, int hour, int min, String tzname) {
        this.pretty = pretty;
        this.year = year;
        this.mon = mon;
        this.mday = mday;
        this.hour = hour;
        this.min = min;
        this.tzname = tzname;
    }

    public String getPretty() {
        return pretty;
    }

    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMon() {
        return mon;
    }

    public void setMon(int mon) {
        this.mon = mon;
    }

    public int getMday() {
        return mday;
    }

    public void setMday(int mday) {
        this.mday = mday;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getTzname() {
        return tzname;
    }

    public void setTzname(String tzname) {
        this.tzname = tzname;
    }

    @Override
    public String toString() {
        return "MyUTCDate{" +
                "pretty='" + pretty + '\'' +
                ", year=" + year +
                ", mon=" + mon +
                ", mday=" + mday +
                ", hour=" + hour +
                ", min=" + min +
                ", tzname='" + tzname + '\'' +
                '}';
    }
}
