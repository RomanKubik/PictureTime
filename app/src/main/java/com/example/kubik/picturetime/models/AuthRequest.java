package com.example.kubik.picturetime.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/13/17.
 */

public class AuthRequest {

    @SerializedName("client_id")
    private String client_id;
    @SerializedName("client_secret")
    private String client_secret;
    @SerializedName("redirect_uri")
    private String redirect_uri;
    @SerializedName("code")
    private String code;
    @SerializedName("grant_type")
    private String grant_type;

    public AuthRequest(String client_id, String client_secret, String redirect_uri, String code, String grant_type) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.redirect_uri = redirect_uri;
        this.code = code;
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
