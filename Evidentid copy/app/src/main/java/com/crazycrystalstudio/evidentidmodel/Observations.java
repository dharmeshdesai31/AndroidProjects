package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Observations {
    private MyDate date;
    private MyUTCDate utcdate;
    private float tempm;
    private float tempi;
    private float dewptm;
    private float dewpti;
    private float hum;
    // many more
    private float wspdm;
    private float wspdi;
    private float wgustm;
    private float wgusti;
    private float wdird;
    private String wdire;
    private float vism;
    private float visi;
    private float pressurem;
    private float pressurei;
    private int windchillm;
    private int windchilli;
    private int heatindexm;
    private int heatindexi;
    private float precipm;
    private float precipi;
    private String conds;
    private String icon;
//    int fog;
//    int rain;
//    int snow;
//    int hail;
//    int thunder;
//    int tornado;
    //String metar;


    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }

    public MyUTCDate getUtcdate() {
        return utcdate;
    }

    public void setUtcdate(MyUTCDate utcdate) {
        this.utcdate = utcdate;
    }

    public float getTempm() {
        return tempm;
    }

    public void setTempm(float tempm) {
        this.tempm = tempm;
    }

    public float getTempi() {
        return tempi;
    }

    public void setTempi(float tempi) {
        this.tempi = tempi;
    }

    public float getDewptm() {
        return dewptm;
    }

    public void setDewptm(float dewptm) {
        this.dewptm = dewptm;
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

    public float getWspdm() {
        return wspdm;
    }

    public void setWspdm(float wspdm) {
        this.wspdm = wspdm;
    }

    public float getWspdi() {
        return wspdi;
    }

    public void setWspdi(float wspdi) {
        this.wspdi = wspdi;
    }

    public float getWgustm() {
        return wgustm;
    }

    public void setWgustm(float wgustm) {
        this.wgustm = wgustm;
    }

    public float getWgusti() {
        return wgusti;
    }

    public void setWgusti(float wgusti) {
        this.wgusti = wgusti;
    }

    public float getWdird() {
        return wdird;
    }

    public void setWdird(float wdird) {
        this.wdird = wdird;
    }

    public String getWdire() {
        return wdire;
    }

    public void setWdire(String wdire) {
        this.wdire = wdire;
    }

    public float getVism() {
        return vism;
    }

    public void setVism(float vism) {
        this.vism = vism;
    }

    public float getVisi() {
        return visi;
    }

    public void setVisi(float visi) {
        this.visi = visi;
    }

    public float getPressurem() {
        return pressurem;
    }

    public void setPressurem(float pressurem) {
        this.pressurem = pressurem;
    }

    public float getPressurei() {
        return pressurei;
    }

    public void setPressurei(float pressurei) {
        this.pressurei = pressurei;
    }

    public int getWindchillm() {
        return windchillm;
    }

    public void setWindchillm(int windchillm) {
        this.windchillm = windchillm;
    }

    public int getWindchilli() {
        return windchilli;
    }

    public void setWindchilli(int windchilli) {
        this.windchilli = windchilli;
    }

    public int getHeatindexm() {
        return heatindexm;
    }

    public void setHeatindexm(int heatindexm) {
        this.heatindexm = heatindexm;
    }

    public int getHeatindexi() {
        return heatindexi;
    }

    public void setHeatindexi(int heatindexi) {
        this.heatindexi = heatindexi;
    }

    public float getPrecipm() {
        return precipm;
    }

    public void setPrecipm(float precipm) {
        this.precipm = precipm;
    }

    public float getPrecipi() {
        return precipi;
    }

    public void setPrecipi(float precipi) {
        this.precipi = precipi;
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


    public Observations() {
    }

   /* public Observations(MyDate date, MyUTCDate utcdate, float tempm, float tempi, float dewptm, float dewpti, float hum) {
        this.date = date;
        this.utcdate = utcdate;
        this.tempm = tempm;
        this.tempi = tempi;
        this.dewptm = dewptm;
        this.dewpti = dewpti;
        this.hum = hum;
    }*/

    @Override
    public String toString() {
        return "Observations{" +
                "date=" + date +
                ", utcdate=" + utcdate +
                ", tempm=" + tempm +
                ", tempi=" + tempi +
                ", dewptm=" + dewptm +
                ", dewpti=" + dewpti +
                ", hum=" + hum +
                ", wspdm=" + wspdm +
                ", wspdi=" + wspdi +
                ", wgustm=" + wgustm +
                ", wgusti=" + wgusti +
                ", wdird=" + wdird +
                ", wdire='" + wdire + '\'' +
                ", vism=" + vism +
                ", visi=" + visi +
                ", pressurem=" + pressurem +
                ", pressurei=" + pressurei +
                ", windchillm=" + windchillm +
                ", windchilli=" + windchilli +
                ", heatindexm=" + heatindexm +
                ", heatindexi=" + heatindexi +
                ", precipm=" + precipm +
                ", precipi=" + precipi +
                ", conds='" + conds + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
