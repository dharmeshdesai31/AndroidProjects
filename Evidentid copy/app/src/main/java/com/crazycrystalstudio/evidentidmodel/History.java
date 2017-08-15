package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by DharmeshDesai on 8/12/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class History {

    private MyDate date;
    private MyUTCDate utcdate;
    private Observations observations[];
    private DailySummary dailysummary[];

    public History() {
    }

    public History(MyDate date, MyUTCDate utcdate, Observations []observations) {
        this.date = date;
        this.utcdate = utcdate;
        this.observations = observations;
    }

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

    public Observations[] getObservations() {
        return observations;
    }

    public Observations getObservation(int index) {
        return observations[index];
    }

    public void setObservations(Observations[] observations) {
        this.observations = observations;
    }

    public DailySummary[] getDailysummary() {
        return dailysummary;
    }

    public DailySummary getDailysummary(int index) {
        return dailysummary[index];
    }

    public void setDailysummary(DailySummary[] dailysummary) {
        this.dailysummary = dailysummary;
    }

    @Override
    public String toString() {
        return "History{" +
                "date=" + date +
                ", utcdate=" + utcdate +
                ", observations=" + Arrays.toString(observations) +
                ", dailysummary=" + Arrays.toString(dailysummary) +
                '}';
    }
}
