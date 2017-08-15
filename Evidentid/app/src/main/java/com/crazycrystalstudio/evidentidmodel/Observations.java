package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Observations {
    private MyDate date;

    private float tempi;
    private float dewpti;
    private float hum;
    private float wspdi;
    private float visi;
    private String conds;
    private String icon;

    public Observations() {
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }

    public float getTempi() {
        return tempi;
    }

    public void setTempi(float tempi) {
        this.tempi = tempi;
    }

    public float getDewpti() {
        return dewpti;
    }

    public void setDewpti(float dewpti) {
        this.dewpti = dewpti;
    }

    public float getHum() {
        return hum;
    }

    public void setHum(float hum) {
        this.hum = hum;
    }

    public float getWspdi() {
        return wspdi;
    }

    public void setWspdi(float wspdi) {
        this.wspdi = wspdi;
    }

    public float getVisi() {
        return visi;
    }

    public void setVisi(float visi) {
        this.visi = visi;
    }

    public String getConds() {
        return conds;
    }

    public void setConds(String conds) {
        this.conds = conds;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Observations{" +
                "date=" + date +
                ", tempi=" + tempi +
                ", dewpti=" + dewpti +
                ", hum=" + hum +
                ", wspdi=" + wspdi +
                ", visi=" + visi +
                ", conds='" + conds + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
