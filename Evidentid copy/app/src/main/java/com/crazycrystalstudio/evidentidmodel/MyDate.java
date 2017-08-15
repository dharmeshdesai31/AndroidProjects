package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MyDate {
    private String pretty;

    public MyDate() {
    }

    public String getPretty() {
        return pretty;
    }

    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "pretty='" + pretty + '\'' +
                '}';
    }
}
