package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class DailySummary {
    private int mintempi;
    private int maxtempi;
    private int meanwindspdi;
    private int meanvisi;


    private MyDate date;
    private int fog;
    private int rain;
    private int snow;
    private float snowfallm;
    //float snowfalli;
    /*String monthtodatesnowfallm;
    String monthtodatesnowfalli;
    String since1julsnowfallm;
    String since1julsnowfalli;
    String snowdepthm;
    String snowdepthi;
    int hail;*/
    private int thunder;
    private int tornado;
    /*int meantempm;
    int meantempi;
    int meandewptm;
    int meandewpti;
    float meanpressurem;
    float meanpressurei;

    int meanwindspdi;
    String meanwdire;
    int meanwdird;

    int meanvisi;*/
    int meanvism;
    int meanwindspdm;
    String humidity;
    private int maxtempm;
    //int maxtempi;
    private int mintempm;
    //int mintempi;
    private int maxhumidity;
    private int minhumidity;
    /*int maxdewptm;
    int maxdewpti;
    int mindewptm;
    int mindewpti;*/
    private int maxpressurem;
   /* float maxpressurei;
    int minpressurem;
    float minpressurei;
    int maxwspdm;
    int maxwspdi;
    int minwspdm;
    int minwspdi;
    int maxvism;
    int maxvisi;
    int minvism;
    int minvisi;
    int gdegreedays;
    int heatingdegreedays;
    int coolingdegreedays;
    float precipm;
    float precipi;
    String precipsource;
    String heatingdegreedaysnormal;
    String monthtodateheatingdegreedays;
    String monthtodateheatingdegreedaysnormal;
    String since1sepheatingdegreedays;
    String since1sepheatingdegreedaysnormal;
    String since1julheatingdegreedays;
    String since1julheatingdegreedaysnormal;
    String coolingdegreedaysnormal;
    String monthtodatecoolingdegreedays;
    String monthtodatecoolingdegreedaysnormal;
    String since1sepcoolingdegreedays;
    String since1sepcoolingdegreedaysnormal;
    String since1jancoolingdegreedays;
    String since1jancoolingdegreedaysnormal;*/

    public DailySummary() {
    }

    public DailySummary(MyDate date, int fog, int rain, int snow, float snowfallm, int thunder, int tornado, String humidity, int maxtempm, int mintempm, int maxhumidity, int minhumidity, int maxpressurem) {
        this.date = date;
        this.fog = fog;
        this.rain = rain;
        this.snow = snow;
        this.snowfallm = snowfallm;
        this.thunder = thunder;
        this.tornado = tornado;
        this.humidity = humidity;
        this.maxtempm = maxtempm;
        this.mintempm = mintempm;
        this.maxhumidity = maxhumidity;
        this.minhumidity = minhumidity;
        this.maxpressurem = maxpressurem;
    }

    public int getMeanvism() {
        return meanvism;
    }

    public void setMeanvism(int meanvism) {
        this.meanvism = meanvism;
    }

    public int getMeanwindspdm() {
        return meanwindspdm;
    }

    public void setMeanwindspdm(int meanwindspdm) {
        this.meanwindspdm = meanwindspdm;
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }

    public int getFog() {
        return fog;
    }

    public void setFog(int fog) {
        this.fog = fog;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public int getSnow() {
        return snow;
    }

    public void setSnow(int snow) {
        this.snow = snow;
    }

    public float getSnowfallm() {
        return snowfallm;
    }

    public void setSnowfallm(float snowfallm) {
        this.snowfallm = snowfallm;
    }

    public int getThunder() {
        return thunder;
    }

    public void setThunder(int thunder) {
        this.thunder = thunder;
    }

    public int getTornado() {
        return tornado;
    }

    public void setTornado(int tornado) {
        this.tornado = tornado;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public int getMaxtempm() {
        return maxtempm;
    }

    public void setMaxtempm(int maxtempm) {
        this.maxtempm = maxtempm;
    }

    public int getMintempm() {
        return mintempm;
    }

    public void setMintempm(int mintempm) {
        this.mintempm = mintempm;
    }

    public int getMaxhumidity() {
        return maxhumidity;
    }

    public void setMaxhumidity(int maxhumidity) {
        this.maxhumidity = maxhumidity;
    }

    public int getMinhumidity() {
        return minhumidity;
    }

    public void setMinhumidity(int minhumidity) {
        this.minhumidity = minhumidity;
    }

    public int getMaxpressurem() {
        return maxpressurem;
    }

    public void setMaxpressurem(int maxpressurem) {
        this.maxpressurem = maxpressurem;
    }
}
