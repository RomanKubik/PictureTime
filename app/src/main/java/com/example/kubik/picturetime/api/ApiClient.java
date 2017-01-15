package com.example.kubik.picturetime.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kubik on 1/12/17.
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.unsplash.com/";
    public static final String OAUTH_URL = "https://unsplash.com/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getOAuthClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(OAUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
