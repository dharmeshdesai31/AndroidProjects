package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MyDate {
    private String pretty;
    private int year;
    private int mon;
    private int mday;


    public MyDate() {
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

    @Override
    public String toString() {
        return "MyDate{" +
                "pretty='" + pretty + '\'' +
                ", year=" + year +
                ", mon=" + mon +
                ", mday=" + mday +
                '}';
    }
}
