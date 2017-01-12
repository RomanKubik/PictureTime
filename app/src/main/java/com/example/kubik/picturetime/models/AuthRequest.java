package com.example.kubik.picturetime.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/13/17.
 */

public class AuthRequest {

    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String secret;
    @SerializedName("redirected_uri")
    private String uri;
    @SerializedName("code")
    private String code;
    @SerializedName("grant_type")
    private String type;

    public AuthRequest(String clientId, String secret, String uri, String code, String type) {
        this.clientId = clientId;
        this.secret = secret;
        this.uri = uri;
        this.code = code;
        this.type = type;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
