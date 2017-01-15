package com.example.kubik.picturetime.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/13/17.
 */

public class AuthResponse {
    @SerializedName("access_token")
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
