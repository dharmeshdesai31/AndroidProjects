package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by DharmeshDesai on 8/12/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class History {

    private Observations observations[];
    private DailySummary dailysummary[];

    public History() {
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
                "observations=" + Arrays.toString(observations) +
                ", dailysummary=" + Arrays.toString(dailysummary) +
                '}';
    }
}
