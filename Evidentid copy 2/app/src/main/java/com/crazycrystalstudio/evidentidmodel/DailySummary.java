package com.crazycrystalstudio.evidentidmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class DailySummary implements Parcelable{
    private float mintempi;
    private float maxtempi;
    private float meanwindspdi;
    private float meanvisi;

    public DailySummary() {
    }

    public DailySummary(DailySummary summery) {
        this.mintempi = summery.getMintempi();
        this.maxtempi = summery.getMaxtempi();
        this.meanwindspdi = summery.getMeanwindspdi();
        this.meanvisi = summery.getMeanvisi();
    }

    public float getMintempi() {
        return mintempi;
    }

    public void setMintempi(float mintempi) {
        this.mintempi = mintempi;
    }

    public float getMaxtempi() {
        return maxtempi;
    }

    public void setMaxtempi(float maxtempi) {
        this.maxtempi = maxtempi;
    }

    public float getMeanwindspdi() {
        return meanwindspdi;
    }

    public void setMeanwindspdi(float meanwindspdi) {
        this.meanwindspdi = meanwindspdi;
    }

    public float getMeanvisi() {
        return meanvisi;
    }

    public void setMeanvisi(float meanvisi) {
        this.meanvisi = meanvisi;
    }

    @Override
    public String toString() {
        return "DailySummary{" +
                "mintempi=" + mintempi +
                ", maxtempi=" + maxtempi +
                ", meanwindspdi=" + meanwindspdi +
                ", meanvisi=" + meanvisi +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mintempi);
        parcel.writeFloat(this.maxtempi);
        parcel.writeFloat(this.meanwindspdi);
        parcel.writeFloat(this.meanvisi);
    }

    private DailySummary(Parcel p){
        this.mintempi = p.readInt();
        this.maxtempi = p.readInt();
        this.meanwindspdi = p.readInt();
        this.meanvisi = p.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public DailySummary createFromParcel(Parcel parcel){
            return new DailySummary(parcel);
        }
        public DailySummary[] newArray(int size){
            return new DailySummary[size];
        }
    };
}
