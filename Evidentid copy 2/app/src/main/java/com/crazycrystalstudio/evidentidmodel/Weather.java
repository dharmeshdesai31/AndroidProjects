package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Weather {
    History history;

    public Weather() {
    }

    public Weather( History history) {
        this.history = history;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Weather{" +
                ", history=" + history +
                '}';
    }
}
