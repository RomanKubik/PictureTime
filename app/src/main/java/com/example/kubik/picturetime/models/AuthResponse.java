package com.example.kubik.picturetime.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/13/17.
 */

public class AuthResponse {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
