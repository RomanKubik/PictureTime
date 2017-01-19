package com.example.kubik.picturetime.models.photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/18/17.
 */

public class PhotoLocation {

    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return country + ", " + city;
    }
}
