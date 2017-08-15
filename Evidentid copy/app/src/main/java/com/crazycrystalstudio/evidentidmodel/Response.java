package com.crazycrystalstudio.evidentidmodel;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

public class Response {
    float version;
    String termsofService;
    Features features;

    public Response() {
    }

    public Response(float version, String termsofService, Features features) {
        this.version = version;
        this.termsofService = termsofService;
        this.features = features;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public String getTermsofService() {
        return termsofService;
    }

    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Response{" +
                "version=" + version +
                ", termsofService='" + termsofService + '\'' +
                ", features=" + features +
                '}';
    }
}
