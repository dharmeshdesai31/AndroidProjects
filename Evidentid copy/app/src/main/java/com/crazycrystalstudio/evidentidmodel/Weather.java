package com.crazycrystalstudio.evidentidmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Weather {
    Response response;
    History history;

    public Weather() {
    }

    public Weather( History history) {
       // this.response = response;
        this.history = history;
    }

   /* public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }*/

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Weather{" +
               // "response=" + response +
                ", history=" + history +
                '}';
    }
}
