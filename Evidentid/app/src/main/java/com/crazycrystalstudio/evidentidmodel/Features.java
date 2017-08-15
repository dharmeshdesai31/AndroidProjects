package com.crazycrystalstudio.evidentidmodel;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

public class Features {
    int history;

    public Features() {
    }

    public Features(int history) {
        this.history = history;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Features{" +
                "history=" + history +
                '}';
    }
}
