package com.example.kubik.picturetime.models.photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/12/17.
 */
public class Urls {

    @SerializedName("regular")
    private String regular;

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
