package com.example.kubik.picturetime.models.photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/18/17.
 */

public class CameraSettings {

    @SerializedName("make")
    private String make;
    @SerializedName("model")
    private String model;
    @SerializedName("exposure_time")
    private String exposureTime;
    @SerializedName("aperture")
    private String aperture;
    @SerializedName("focal_length")
    private String focalLength;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(String exposureTime) {
        this.exposureTime = exposureTime;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public String getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(String focalLength) {
        this.focalLength = focalLength;
    }

    @Override
    public String toString() {
        return "Camera: " + make + '\n' +
                "Model: " + model + '\n' +
                "Exposure time: " + exposureTime + '\n' +
                "Aperture: " + aperture + '\n' +
                "Focal length: " + focalLength + '\n';
    }
}
